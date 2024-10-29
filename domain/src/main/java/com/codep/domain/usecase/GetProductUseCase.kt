package com.codep.domain.usecase

import com.codep.domain.repository.ProductRepository

class GetProductUseCase (private val repository: ProductRepository) {
    suspend fun execute(category:String?) = repository.getProducts(category)
}