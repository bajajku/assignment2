package sheridan.bajajku.assignment2.ui.model

import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product

data class ListProductModel(
    val id: Int,
    val name: String ,
    val brand: String,
    val priority: Priority
){
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        brand = product.brand,
        priority = product.priority
    )

    constructor(): this(Product())
}

fun Product.toListItemModel() = ListProductModel(this)
