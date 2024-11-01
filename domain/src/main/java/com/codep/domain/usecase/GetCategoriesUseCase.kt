package com.codep.domain.usecase

import com.codep.domain.repository.CategoryRepository

class GetCategoriesUseCase (private val repository: CategoryRepository) {
    suspend fun execute() = repository.getCategories()
}