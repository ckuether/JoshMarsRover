package com.example.joshmarsrover.domain.model

sealed class ResponseWrapper<out T> {
    object Loading: ResponseWrapper<Nothing>()
    data class Success<out T>(val data: T): ResponseWrapper<T>()
    data class Failure(val e: Exception?): ResponseWrapper<Nothing>()
}