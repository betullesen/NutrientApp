package com.betulesen.nutrientbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.betulesen.nutrientbook.model.nutrient
import com.betulesen.nutrientbook.roomDb.NutrientDatabase
import kotlinx.coroutines.launch

class NutrientDetailViewModel(application : Application) : AndroidViewModel(application) {

    val nutrientLiveData = MutableLiveData<nutrient>()

    fun takeRoomData ( uuid : Int) {
        viewModelScope.launch {
            val dao = NutrientDatabase(getApplication()).nutrientDao()
            val nutrient = dao.getNutrient(uuid)
            nutrientLiveData.value = nutrient
        }

    }
}