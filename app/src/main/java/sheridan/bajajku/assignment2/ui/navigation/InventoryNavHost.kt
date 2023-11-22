package sheridan.bajajku.assignment2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import sheridan.bajajku.assignment2.domain.Product
import sheridan.bajajku.assignment2.ui.home.HomeScreen
import sheridan.bajajku.assignment2.ui.home.HomeViewModel
import sheridan.bajajku.assignment2.ui.product.details.ProductDetailsScreen
import sheridan.bajajku.assignment2.ui.product.details.ProductDetailsViewModel
import sheridan.bajajku.assignment2.ui.product.edit.ProductEditScreen
import sheridan.bajajku.assignment2.ui.product.edit.ProductEditViewModel
import sheridan.bajajku.assignment2.ui.product.entry.ProductEntryScreen
import sheridan.bajajku.assignment2.ui.product.entry.ProductEntryViewModel

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navigateToProductEntry = { navController.navigate(ProductEntryDestination.route) },
                navigateToProductDetails = { id->
                    navController.navigate("${ProductDetailsDestination.route}/${id}")
                },
                viewModel = viewModel
            )
        }
        composable(route = ProductEntryDestination.route) {
            val viewModel: ProductEntryViewModel = hiltViewModel()
            ProductEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(
            route = ProductDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductDetailsDestination.productIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ProductDetailsViewModel = hiltViewModel()
            ProductDetailsScreen(
                navigateToEditProduct = { navController.navigate("${ProductEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(
            route = ProductEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductEditDestination.productIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ProductEditViewModel = hiltViewModel()
            ProductEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
    }
}
