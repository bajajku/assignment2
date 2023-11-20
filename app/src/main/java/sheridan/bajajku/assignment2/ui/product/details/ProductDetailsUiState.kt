package sheridan.bajajku.assignment2.ui.product.details

import sheridan.bajajku.assignment2.domain.Product
import sheridan.bajajku.assignment2.ui.model.ProductDetailsModel
import sheridan.bajajku.assignment2.ui.model.toProductDetailsModel

data class ProductDetailsUiState(
    val outOfStock: Boolean,

    val product: ProductDetailsModel
) {
    constructor(product: Product) : this(
        outOfStock = product.quantity <= 0,

        product = product.toProductDetailsModel()
    )

    constructor() : this(Product())
}