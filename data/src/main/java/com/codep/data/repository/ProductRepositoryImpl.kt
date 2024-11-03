package com.codep.data.repository

import com.codep.domain.model.Product
import com.codep.domain.network.NetworkService
import com.codep.domain.network.ResultWrapper
import com.codep.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService) : ProductRepository {
    override suspend fun getProducts(category:Int?): ResultWrapper<List<Product>> {
       return networkService.getProducts(category)

    }
}