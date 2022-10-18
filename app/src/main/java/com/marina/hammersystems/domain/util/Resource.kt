package com.marina.hammersystems.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val error: ErrorType? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorType) : Resource<T>(error = error)
    class Loading<T> : Resource<T>()
}

enum class ErrorType {
    INTERNET_CONNECTION,
    UNKNOWN
}