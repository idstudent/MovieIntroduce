package com.example.data.model

abstract class BaseResponse<T> {
    abstract fun mapper() : T
}
