package com.codep.domain.repository

import com.codep.domain.model.Product
import com.codep.domain.network.ResultWrapper

interface ProductRepository {
   suspend fun getProducts(category:String?): ResultWrapper<List<Product>>
}