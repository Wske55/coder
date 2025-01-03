package com.codep.rossin

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codep.rossin.ui.feature.home.HomeScreen
import com.codep.rossin.ui.theme.RossinTheme
import okhttp3.Route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RossinTheme {
                val navController = rememberNavController()
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)

                    }
                ) {
                    Surface(modifier = Modifier.fillMaxSize().padding(it)) {
                        NavHost(navController = navController, startDestination ="home") {
                            composable (route = "home") {
                                HomeScreen(navController)
                            }
                            composable (route = "cart") {
                                Box(modifier = Modifier.fillMaxSize()){
                                    Text(text = "Cart")
                                }
                            }
                            composable (route = "profile") {
                                Box(modifier = Modifier.fillMaxSize()){
                                    Text(text = "Profile")
                                }
                            }

                        }
                    }

                }

            }
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val items = listOf(
            BottomNavItems.Home,
            BottomNavItems.Cart,
            BottomNavItems.Profile
        )
        items.forEach{ item ->
            NavigationBarItem(
                selected = currentRoute ==item.route,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { startRoute ->
                            popUpTo(startRoute){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = { Text(text = item.title) },
                icon ={
                    Image(painter = painterResource(id = item.icon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(if (currentRoute==item.route)MaterialTheme.colorScheme.primary else Color.Gray)
                    )

                }, colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )

            )

        }
    }

}

sealed class BottomNavItems(val route: String, val title: String,val icon: Int){
    object Home : BottomNavItems("home", "Home",icon = R.drawable.ic_home)
    object Cart : BottomNavItems("cart", "Cart",icon = R.drawable.ic_cart)
    object Profile : BottomNavItems("profile", "Profile",icon = R.drawable.ic_profile_bn)
}