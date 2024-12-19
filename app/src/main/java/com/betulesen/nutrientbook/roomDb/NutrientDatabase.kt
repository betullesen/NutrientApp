package com.betulesen.nutrientbook.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betulesen.nutrientbook.databinding.NutrientRecyclerRowBinding
import com.betulesen.nutrientbook.model.nutrient

@Database(entities = [nutrient::class], version = 1)
abstract class NutrientDatabase : RoomDatabase() {
    abstract fun nutrientDao() : NutrientDAO

    companion object{

        @Volatile
        private var instance : NutrientDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseCreate(context).also {
                instance=it
            }
        }

        private fun databaseCreate(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NutrientDatabase::class.java,
            "NutrientDatabase"
        ).build()
    }
}

