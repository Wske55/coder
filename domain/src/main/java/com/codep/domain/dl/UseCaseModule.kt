package com.codep.domain.dl

import com.codep.domain.usecase.GetCategoriesUseCase
import com.codep.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase (get()) }
}