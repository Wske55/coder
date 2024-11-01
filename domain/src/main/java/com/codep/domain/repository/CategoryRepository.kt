package com.codep.domain.repository

import com.codep.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories(): ResultWrapper<List<String>>

}