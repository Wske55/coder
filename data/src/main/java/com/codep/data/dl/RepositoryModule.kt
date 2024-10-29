package com.codep.data.dl

import com.codep.data.repository.ProductRepositoryImpl
import com.codep.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}