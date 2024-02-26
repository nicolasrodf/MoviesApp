package com.nicolasrf.moviesapp.data

import retrofit2.HttpException
import java.io.IOException
import com.nicolasrf.moviesapp.domain.model.Error

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}