package sheridan.bajajku.assignment2.ui.navigation

import sheridan.bajajku.assignment2.R

object ProductEditDestination : NavigationDestination {
    override val route = "product_edit"
    override val titleRes = R.string.edit_product_title
    const val productIdArg = "productId"
    val routeWithArgs = "$route/{$productIdArg}"
}