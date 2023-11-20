package sheridan.bajajku.assignment2.ui.model

import sheridan.bajajku.assignment2.domain.Product

data class ProductDetailsModel(
    val id: Int,
    val name: String ,
    val brand: String,
){
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        brand = product.brand,
    )

    constructor(): this(Product())
}

fun Product.toProductDetailsModel() = ProductDetailsModel(this)


