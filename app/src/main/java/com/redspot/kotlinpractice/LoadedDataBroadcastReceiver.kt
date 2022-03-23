package com.redspot.kotlinpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import java.lang.Exception

class LoadedDataBroadcastReceiver : BroadcastReceiver() {
    private val data = MutableLiveData<AppState>()

    override fun onReceive(context: Context?, intent: Intent?) {
        data.value = AppState.Loading
        try {
            data.value =
                intent?.getParcelableArrayListExtra<MoviesCategory>(LoadingService.INTENT_SERVICE_DATA)
                    ?.let {
                        AppState.Success(
                            it
                        )
                    }
        } catch (error: Exception) {
            error.message?.let {
                data.value = AppState.Failure(it)
            }
        }
    }

    fun getData() = data
}