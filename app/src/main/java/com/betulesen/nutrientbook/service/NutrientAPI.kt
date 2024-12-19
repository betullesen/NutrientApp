package com.betulesen.nutrientbook.service

import com.betulesen.nutrientbook.model.nutrient
import retrofit2.http.GET

interface NutrientAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    // BASE_URL -> https://raw.githubusercontent.com/
    // ENDPOINT -> atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getNutrient() : List<nutrient>
}