package com.example.jetpackdogs.Utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object {
        private const val  PREF_TIME = "Pref_time"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedPreferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper = instance ?: synchronized(
            LOCK
        ) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    fun saveUpdateTime(time : Long){
        // extension function
        prefs?.edit(commit = true){
            putLong(PREF_TIME,time)
        }
    }

    fun getUpdateTime():Long?{
        return prefs?.getLong(PREF_TIME,0)
    }
}