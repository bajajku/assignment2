package sheridan.bajajku.assignment2.ui.product.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment2.data.repository.ProductsRepository
import sheridan.bajajku.assignment2.ui.navigation.ProductDetailsDestination
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle[ProductDetailsDestination.productIdArg])

    /**
     * Holds the product details ui state. The data is retrieved from [ProductsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ProductDetailsUiState> =
        productsRepository.getProductByIdStream(productId)
            .filterNotNull()
            .map { product ->
                ProductDetailsUiState(product)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ProductDetailsUiState()
            )

    /**
     * Reduces the product quantity by one and update the [ProductsRepository]'s data source.
     */
    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentProduct = uiState.value.product
            if (currentProduct.quantity > 0) {
                productsRepository.updateProductQuantityById(currentProduct.id, currentProduct.quantity - 1)
            }
        }
    }

    /**
     * Deletes the product from the [ProductsRepository]'s data source.
     */
    fun deleteProduct() = viewModelScope.launch{
        productsRepository.deleteProductById(uiState.value.product.id)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

