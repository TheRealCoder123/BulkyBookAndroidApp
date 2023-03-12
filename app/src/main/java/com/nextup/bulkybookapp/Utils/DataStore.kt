package com.nextup.bulkybookapp.Utils


import android.content.Context
import android.content.SharedPreferences

class DataStore(context: Context)  {

    private val sharedPreference: SharedPreferences =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

    fun tags() : DataStoreTags{
        return  DataStoreTags
    }

    fun saveString(tag: String, data: String?){
        val editor = sharedPreference.edit()
        editor.putString(tag, data)
        editor.apply()
    }

    fun saveBoolean(tag: String, value: Boolean){
        val editor = sharedPreference.edit()
        editor.putBoolean(tag, value)
        editor.apply()
    }

    fun readString(tag: String): String?{
        return sharedPreference.getString(tag, null)
    }

    fun readBoolean(tag: String): Boolean {
        return sharedPreference.getBoolean(tag, false)
    }

    fun delete(key: String){
        val editor = sharedPreference.edit()
        editor.remove(key).apply()
    }


}