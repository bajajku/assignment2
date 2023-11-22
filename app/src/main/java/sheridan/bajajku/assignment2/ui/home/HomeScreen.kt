package sheridan.bajajku.assignment2.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sheridan.bajajku.assignment2.R
import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product
import sheridan.bajajku.assignment2.ui.common.InventoryTopAppBar
import sheridan.bajajku.assignment2.ui.model.ListProductModel
import sheridan.bajajku.assignment2.ui.model.toListProductModel
import sheridan.bajajku.assignment2.ui.navigation.HomeDestination
import sheridan.bajajku.assignment2.ui.theme.InventoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToProductEntry: () -> Unit,
    navigateToProductDetails: (Int) -> Unit,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val homeUiState:HomeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InventoryTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToProductEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.product_entry_title)
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            productList = homeUiState.productList,
            onProductClick = navigateToProductDetails,
            onToggleSelect = viewModel::toggleSelect,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    productList: List<ListProductModel>,
    onProductClick: (Int) -> Unit,
    onToggleSelect: (ListProductModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (productList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_product_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            InventoryList(
                productList = productList,
                onProductClick = onProductClick,
                onToggleSelect = onToggleSelect,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun InventoryList(
    productList: List<ListProductModel>,
    onProductClick: (Int) -> Unit,
    onToggleSelect: (ListProductModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = productList, key = { it.id }) { product ->
            InventoryProduct(
                product = product,
                onToggleSelect = onToggleSelect,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onProductClick(product.id) })
        }
    }
}

@Composable
private fun InventoryProduct(
    product: ListProductModel,
    onToggleSelect: (ListProductModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Checkbox(checked = product.selected, onCheckedChange = { onToggleSelect(product)})
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = stringResource(R.string.in_stock, product.quantity),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    InventoryTheme {
        HomeBody(listOf(
            Product(1, "Phone", "Apple", 20).toListProductModel(),
            Product(2, "Pen", "BLX", 100, Priority.High).toListProductModel(),
            Product(3, "TV", "Lenovo", 2).toListProductModel()
        ), onProductClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    InventoryTheme {
        HomeBody(listOf(), onProductClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryProductPreview() {
    InventoryTheme {
        InventoryProduct(
            Product(1, "Game", "Sony", 20).toListProductModel(),
            onToggleSelect = {}
        )
    }
}
