package com.khairy.core.helpers.base

sealed class BaseResponseWrapper {
    class Success<T>(val data: T) : BaseResponseWrapper()
    class Failed(val message: String?) : BaseResponseWrapper()
    object NetworkError : BaseResponseWrapper()
    object ServerError : BaseResponseWrapper()
}