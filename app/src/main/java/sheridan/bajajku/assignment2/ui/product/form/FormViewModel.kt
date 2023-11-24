package sheridan.bajajku.assignment2.ui.product.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.ui.model.ProductFormModel

abstract class FormViewModel(
): ViewModel() {

    var uiState: ProductFormUiState by mutableStateOf(ProductFormUiState())
        protected set


    fun onNameChange(newName: String) =
        updateUiState(uiState.productFormModel.copy(name = newName))

    fun onBrandChange(newBrand: String) =
        updateUiState(uiState.productFormModel.copy(brand = newBrand))

    fun onQuantityChange(newQuantity: String) =
        updateUiState(uiState.productFormModel.copy(quantity = newQuantity))

    fun onPrioritySelected(newPriority: Priority) =
        updateUiState(uiState.productFormModel.copy(priority = newPriority))



    private fun updateUiState(productFormModel: ProductFormModel) {
        uiState =
            ProductFormUiState(
                productFormModel = productFormModel,
                isEntryValid = productFormModel.isValid()
            )
    }
}