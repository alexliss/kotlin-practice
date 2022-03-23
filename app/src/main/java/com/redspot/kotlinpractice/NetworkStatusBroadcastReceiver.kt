package com.redspot.kotlinpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData

class NetworkStatusBroadcastReceiver : BroadcastReceiver() {
    private val connectionStatusLiveData = MutableLiveData<Boolean>()

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        connectionStatusLiveData.postValue(
            when (connectivityManager.activeNetworkInfo) {
                null -> false
                else -> true
            }
        )
    }

    fun getConnectionStatus() = connectionStatusLiveData
}
