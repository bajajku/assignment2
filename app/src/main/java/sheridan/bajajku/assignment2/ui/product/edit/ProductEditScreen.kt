package sheridan.bajajku.assignment2.ui.product.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sheridan.bajajku.assignment2.ui.common.InventoryTopAppBar
import sheridan.bajajku.assignment2.ui.navigation.ProductEditDestination
import sheridan.bajajku.assignment2.ui.product.form.ProductFormBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ProductEditViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ProductEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ProductFormBody(
            productFormUiState = viewModel.uiState,
            onNameChange = viewModel::onNameChange,
            onBrandChange = viewModel::onBrandChange,
            onQuantityChange = viewModel::onQuantityChange,
            onSaveClick = {
                viewModel.updateProduct()
                navigateBack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProductEditScreenPreview() {
//    InventoryTheme {
//        ProductEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
//    }
//}
