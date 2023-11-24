package sheridan.bajajku.assignment2.ui.model

import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product

data class ProductDetailsModel(
    val id: Int,
    val name: String ,
    val brand: String,
    val quantity: Int,
    val priority: Priority

){
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        brand = product.brand,
        quantity = product.quantity,
        priority = product.priority

    )

    constructor(): this(Product())
}

fun Product.toProductDetailsModel() = ProductDetailsModel(this)


