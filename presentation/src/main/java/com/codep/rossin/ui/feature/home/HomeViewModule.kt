package com.codep.rossin.ui.feature.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codep.domain.model.Product
import com.codep.domain.network.ResultWrapper
import com.codep.domain.usecase.GetCategoriesUseCase
import com.codep.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.logger.MESSAGE

class HomeViewModule(private val getProductUseCase: GetProductUseCase, private val categoriesUseCase: GetCategoriesUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        getAllProducts()
    }
    private fun getAllProducts(){
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts("electronics")
            val popularProducts = getProducts("jewelery")
            val categories = getCategory()
            if (featured.isEmpty() || popularProducts.isEmpty()&& categories.isNotEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load Product")
            }
            _uiState.value = HomeScreenUIEvents.Success(featured, popularProducts,categories)
        }
    }
    private  suspend fun getCategory(): List<String>{
        categoriesUseCase.execute().let { result ->
            when (result) {
                is ResultWrapper.Success ->{
                    return (result).value
                }
                is ResultWrapper.Failure ->{
                    return emptyList()
                }
            }
        }
    }

  private suspend fun getProducts(category:String?): List<Product> {
       getProductUseCase.execute(category).let { result ->
           when (result) {
               is ResultWrapper.Success -> {
                   return (result).value


               }

               is ResultWrapper.Failure -> {
                   return emptyList()

               }

           }
       }
   }
}

sealed class HomeScreenUIEvents{
    data object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<Product>,
        val popularProducts: List<Product>,
        val categories: List<String>):
        HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()
}