package com.betulesen.nutrientbook.util

import android.content.Context
import android.content.SharedPreferences

class PrivateSharedPreferences {
    companion object {

        private val TIME = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile
        private var instance: PrivateSharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: privateSharedPreferencesCreate(context).also {
                instance = it
            }
        }

        private fun privateSharedPreferencesCreate(context: Context) : PrivateSharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return PrivateSharedPreferences()
        }

    }

    fun timeSave(time : Long){
        sharedPreferences?.edit()?.putLong(TIME,time)?.apply()
    }

    fun timeTake() = sharedPreferences?.getLong(TIME,0)
}