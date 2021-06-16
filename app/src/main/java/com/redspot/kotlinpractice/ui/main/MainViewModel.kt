package com.redspot.kotlinpractice.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redspot.kotlinpractice.model.AllCategory
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.repsitory.Repository
import com.redspot.kotlinpractice.model.repsitory.RepositoryImpl

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: Repository = RepositoryImpl()

    fun getLiveData() = liveDataToObserve

    fun getCategories() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {

            Thread.sleep(1000)

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