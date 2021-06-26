package com.redspot.kotlinpractice.framework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.repository.Repository

private const val WAIT_TIME: Long = 1000

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getCategories() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(WAIT_TIME)
            try {
                liveDataToObserve.postValue(
                       AppState.Success(repository.getCategoriesFromServer())
                )
            } catch (error: Exception) {
                error.message?.let {
                    liveDataToObserve.postValue(AppState.Failure(it))
                }
            }
        }.start()
    }
}