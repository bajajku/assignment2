package sheridan.bajajku.assignment2.ui.product.form

import sheridan.bajajku.assignment2.domain.Product
import sheridan.bajajku.assignment2.ui.model.ProductFormModel
import sheridan.bajajku.assignment2.ui.model.toProductFormData

data class ProductFormUiState(
    val productFormModel: ProductFormModel = ProductFormModel(),
    val isEntryValid: Boolean = false
)

fun Product.toProductFormUiState(isEntryValid: Boolean = false): ProductFormUiState =
    ProductFormUiState(
        productFormModel = this.toProductFormData(),
        isEntryValid = isEntryValid
    )