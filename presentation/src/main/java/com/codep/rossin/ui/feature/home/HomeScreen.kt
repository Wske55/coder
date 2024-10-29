package com.codep.rossin.ui.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.codep.domain.model.Product
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavController,viewModule: HomeViewModule = koinViewModel()){


    val uiState = viewModule.uiState.collectAsState()

    Scaffold {
        Surface (modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            when (uiState.value) {
                is HomeScreenUIEvents.Loading -> {
                    CircularProgressIndicator()
                }
                is HomeScreenUIEvents.Success ->{
                    val data = (uiState.value as HomeScreenUIEvents.Success)
                    HomeContent (data.featured, data.popularProducts)

                }
                is HomeScreenUIEvents.Error ->{
                    Text(text = (uiState.value as HomeScreenUIEvents.Error).message)
                }
            }

        }
    }


}

@Composable
fun HomeContent (featured: List<Product>,popularProducts: List<Product>){
    LazyColumn {
        item {
            if (featured.isNotEmpty()){
                HomeProductRow(products = featured, title = "Featured")
                Spacer(modifier = Modifier.size(16.dp))

            }
            if (popularProducts.isNotEmpty()){
                HomeProductRow(products = popularProducts, title = "Popular Products")
            }
        }
    }
}

@Composable
fun HomeProductRow(products: List<Product>, title:String){
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "View all",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow {
            items(products) { product ->
                ProductItem(product = product)

            }
        }

    }
    
}




@Composable
fun ProductItem(product: Product){
    Card (modifier = Modifier
        .padding(horizontal = 8.dp)
        .size(width = 126.dp, height = 144.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
        ){

        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
            )

            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.SemiBold

            )
            Text(
                text ="$${product.title}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }

    }
}
//test git












