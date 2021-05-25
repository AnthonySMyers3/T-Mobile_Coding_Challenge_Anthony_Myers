package com.example.t_mobilecodingchallengeanthonymyers.utils

sealed class Event<out R> {
    object Loading : Event<Nothing>()
    data class Error(val errorMessage: String? = null) : Event<Nothing>()
    data class Success<T>(val data: T) : Event<T>()
}