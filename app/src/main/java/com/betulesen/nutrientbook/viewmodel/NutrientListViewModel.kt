package com.betulesen.nutrientbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulesen.nutrientbook.model.nutrient
import com.betulesen.nutrientbook.roomDb.NutrientDatabase
import com.betulesen.nutrientbook.service.NutrientAPIService
import com.betulesen.nutrientbook.util.PrivateSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NutrientListViewModel(application: Application) : AndroidViewModel(application) {

    val nutrients = MutableLiveData<List<nutrient>>()
    val nutrientErrorMessage = MutableLiveData<Boolean>()
    val nutrientLoading = MutableLiveData<Boolean>()

    private val nutrientApiService = NutrientAPIService()
    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())

    private val currentTime = 10 * 60 *1000 * 1000 * 1000L

    fun refreshData() {
        val recordingTime = privateSharedPreferences.timeTake()

        if(recordingTime != null && recordingTime != 0L && System.nanoTime() - recordingTime < currentTime) {
            getDataFromRoom()
        }else{
            getDataFromInternet()
        }
    }

    fun refreshDataFromInternet(){
        getDataFromInternet()
    }

    private fun getDataFromRoom(){
        nutrientLoading.value = true

        viewModelScope.launch(Dispatchers.IO ) {
            val nutrientList = NutrientDatabase(getApplication()).nutrientDao().getAllNutrient()
            withContext(Dispatchers.Main) {
                showNutrients(nutrientList)
                Toast.makeText(getApplication(),"data taken from room",Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun getDataFromInternet() {

        nutrientLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutrientList = nutrientApiService.getData()
            withContext(Dispatchers.Main) {
                nutrientLoading.value = false
                saveRoom(nutrientList)
                Toast.makeText(getApplication(),"Data taken from the internet",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showNutrients(nutrientList : List<nutrient>) {
        nutrients.value = nutrientList
        nutrientErrorMessage.value = false
        nutrientLoading.value = false
    }

    private fun saveRoom(nutrientLists : List<nutrient>) {
        viewModelScope.launch {

            val dao = NutrientDatabase(getApplication()).nutrientDao()
            dao.deleteAllNutrient()
            val uuidList = dao.insertAll(*nutrientLists.toTypedArray())
            var i = 0
            while (i < nutrientLists.size) {
                nutrientLists[i].uuid = uuidList[i].toInt()
                i = i + 1
            }

            showNutrients(nutrientLists)

        }

        privateSharedPreferences.timeSave(System.nanoTime())
    }

}