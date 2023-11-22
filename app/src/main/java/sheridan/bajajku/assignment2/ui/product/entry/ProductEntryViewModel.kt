package sheridan.bajajku.assignment2.ui.product.entry

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment2.data.repository.ProductsRepository
import sheridan.bajajku.assignment2.ui.product.form.FormViewModel
import javax.inject.Inject

@HiltViewModel
class ProductEntryViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : FormViewModel() {

    /**
     * Inserts an [LocalProduct] in the Room database
     */
    fun saveProduct() = viewModelScope.launch{
        if (uiState.productFormModel.isValid()) {
            productsRepository.insertProduct(uiState.productFormModel.toProduct())
        }
    }

}

