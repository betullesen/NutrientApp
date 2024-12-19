package com.betulesen.nutrientbook.service

import com.betulesen.nutrientbook.model.nutrient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutrientAPIService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NutrientAPI::class.java)

    suspend fun getData() : List<nutrient> {
        return retrofit.getNutrient()
    }
}