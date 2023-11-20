package sheridan.bajajku.assignment2.ui.model

import sheridan.bajajku.assignment2.domain.Product

data class ProductDetailsModel(
    val id: Int,
    val name: String ,
    val brand: String,
    val quantity: Int

){
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        brand = product.brand,
        quantity = product.quantity

    )

    constructor(): this(Product())
}

fun Product.toProductDetailsModel() = ProductDetailsModel(this)


