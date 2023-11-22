package sheridan.bajajku.assignment2.ui.product.entry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sheridan.bajajku.assignment2.ui.common.InventoryTopAppBar
import sheridan.bajajku.assignment2.ui.model.ProductFormModel
import sheridan.bajajku.assignment2.ui.product.form.ProductFormBody
import sheridan.bajajku.assignment2.ui.product.form.ProductFormUiState
import sheridan.bajajku.assignment2.ui.theme.InventoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ProductEntryViewModel
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ProductEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ProductFormBody(
            productFormUiState = viewModel.uiState,
            onNameChange = viewModel::onNameChange,
            onBrandChange = viewModel::onBrandChange,
            onQuantityChange = viewModel::onQuantityChange,
            onSaveClick = {
                viewModel.saveProduct()
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductEntryScreenPreview() {
    InventoryTheme {
        ProductFormBody(
            productFormUiState = ProductFormUiState(
                ProductFormModel(
                    name = "Product name", brand = "brand name", quantity = "5"
                )
            ),
            onNameChange = {}, onBrandChange = {}, onQuantityChange = {},
            onSaveClick = {}
        )
    }
}
