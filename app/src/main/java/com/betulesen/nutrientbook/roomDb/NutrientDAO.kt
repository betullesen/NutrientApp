package com.betulesen.nutrientbook.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.betulesen.nutrientbook.model.nutrient

@Dao
interface NutrientDAO {

    @Insert
    suspend fun insertAll(vararg nutrient: nutrient) : List<Long>

    @Query("SELECT * FROM nutrient")
    suspend fun getAllNutrient() : List<nutrient>

    @Query("SELECT * FROM nutrient WHERE uuid = :nutrientId")
    suspend fun getNutrient(nutrientId : Int) : nutrient

    @Query("DELETE FROM nutrient")
    suspend fun deleteAllNutrient()
}