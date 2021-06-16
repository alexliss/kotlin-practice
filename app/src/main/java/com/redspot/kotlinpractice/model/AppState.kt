package com.redspot.kotlinpractice.model

sealed class AppState {
    data class Success(val data: Any) : AppState()

    // error закомментирована, потому что я так и не придумала, как ее отловить, а если не поймать - крашится
    data class Failure(/*val error: Throwable*/ val msg: String) : AppState()

    object Loading : AppState()
}