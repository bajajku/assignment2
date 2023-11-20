package sheridan.bajajku.assignment2.ui.navigation

import sheridan.bajajku.assignment2.R

object ProductDetailsDestination : NavigationDestination {
    override val route = "product_details"
    override val titleRes = R.string.product_detail_title
    const val productIdArg = "productId"
    val routeWithArgs = "$route/{$productIdArg}"
}