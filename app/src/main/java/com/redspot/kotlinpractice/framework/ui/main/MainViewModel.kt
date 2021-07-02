package com.redspot.kotlinpractice.framework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.async

private const val WAIT_TIME: Long = 1000

class MainViewModel(private val repository: Repository)
    : ViewModel(), CoroutineScope by MainScope() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getCategories() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        launch {
            delay(WAIT_TIME)
            liveDataToObserve.value = async(Dispatchers.IO) {
                try {
                    return@async AppState.Success(repository.getCategoriesFromServer())
                } catch (error: Exception) {
                    error.message?.let {
                        return@async AppState.Failure(it)
                    }
                }
            }.await()
        }
    }
}