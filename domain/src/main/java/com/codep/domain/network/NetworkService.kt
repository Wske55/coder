package com.codep.domain.network

import com.codep.domain.model.Product
import java.lang.Exception

interface NetworkService {
    suspend fun getProducts(category:Int?): ResultWrapper<List<Product>>
    suspend fun getCategories(): ResultWrapper<List<String>>
}

sealed class ResultWrapper<out T> {
    data class Success<out T> (val value: T) : ResultWrapper<T> ()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing> ()
}