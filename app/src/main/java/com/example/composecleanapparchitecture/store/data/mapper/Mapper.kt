package com.example.composecleanapparchitecture.store.data.mapper

import com.example.composecleanapparchitecture.store.domain.model.ApiError
import com.example.composecleanapparchitecture.store.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

/**
 * mapper in clean arch as name implies does mapping or converting from object A to object B
 */
fun Throwable.toNetworkError(): NetworkError{
    val error = when(this){
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error, t= this
    )
}