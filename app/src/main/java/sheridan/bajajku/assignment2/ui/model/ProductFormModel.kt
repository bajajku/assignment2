package sheridan.bajajku.assignment2.ui.model

import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product

data class ProductFormModel(
    val id: Int = 0,
    val name: String = "",
    val brand: String = "",
    val priority: Priority = Priority.Low,
) {
    fun isValid(): Boolean =
        name.isNotBlank() && brand.isNotBlank()

    fun toProduct(): Product = Product(
        id = id,
        name = name,
        brand = brand,
        priority = priority
    )
}

fun Product.toProductFormData(): ProductFormModel = ProductFormModel(
    id = id,
    name = name,
    brand = brand,
    priority = priority
)
