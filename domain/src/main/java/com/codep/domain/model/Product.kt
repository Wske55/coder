package com.codep.domain.model

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String
) {


     val priceString: String
         get() = "$$price"
}

/*data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val categoryId: Int,
    val description: String,
    val image: String
)*/
