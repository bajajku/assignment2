package sheridan.bajajku.assignment2.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.bajajku.assignment2.ui.navigation.InventoryNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}

