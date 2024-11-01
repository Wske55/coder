package com.codep.data.repository

import com.codep.domain.network.NetworkService
import com.codep.domain.network.ResultWrapper
import com.codep.domain.repository.CategoryRepository

class CategoryRepositoryImpl(val networkService: NetworkService) : CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return networkService.getCategories()
    }
}