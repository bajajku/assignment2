package sheridan.bajajku.assignment2.ui.product.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment2.data.repository.ProductsRepository
import sheridan.bajajku.assignment2.ui.model.ProductFormModel
import sheridan.bajajku.assignment2.ui.navigation.ProductEditDestination
import sheridan.bajajku.assignment2.ui.product.form.FormViewModel
import sheridan.bajajku.assignment2.ui.product.form.toProductFormUiState
import javax.inject.Inject

@HiltViewModel
class ProductEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productsRepository: ProductsRepository
) : FormViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle[ProductEditDestination.productIdArg])

    init {
        viewModelScope.launch {
            uiState = productsRepository.getProductByIdStream(productId)
                .filterNotNull()
                .first()
                .toProductFormUiState(isEntryValid = true)
        }
    }

    /**
     * Update the product in the [ProductsRepository]'s data source
     */
    fun updateProduct() = viewModelScope.launch {
        val formData: ProductFormModel = uiState.productFormModel
        if (formData.isValid()) {
            productsRepository.updateProduct(formData.toProduct())
        }
    }
}
