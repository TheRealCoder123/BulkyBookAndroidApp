package com.nextup.bulkybookapp.domain.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build

class NetworkConnection(private val context: Context) {

    companion object {
        const val INTERNET_ON = true
        const val INTERNET_OFF = false
    }

    fun check() : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            return when{
                capabilities?.hasTransport(TRANSPORT_WIFI)!! -> INTERNET_ON
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> INTERNET_ON
                capabilities.hasTransport(TRANSPORT_ETHERNET)-> INTERNET_ON
                else -> INTERNET_OFF
            }
        }else{
            connectivityManager.activeNetworkInfo.run {
                return when(this!!.type){
                    TYPE_WIFI -> INTERNET_ON
                    TYPE_MOBILE -> INTERNET_ON
                    TYPE_ETHERNET -> INTERNET_ON
                    else -> INTERNET_OFF
                }
            }
        }
    }
}