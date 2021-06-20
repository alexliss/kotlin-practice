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

            // здесь рандом AppState, второй пункт первого задания.
            val randomInteger = (0..1).shuffled().first()
            if (randomInteger == 0) {
                liveDataToObserve.postValue(
                    AppState.Success(repository.getCategoriesFromLocalStorage())
                )
            } else {
                // без понятия, как отсюда ловить ошибку
                liveDataToObserve.postValue(AppState.Failure("Error!"))
            }
        }.start()
    }
}