package sheridan.bajajku.assignment2.domain

data class Product(
    val id: Int = 0,
    val name: String = "No Name",
    val brand: String = "",
    val quantity: Int = 0,
    val priority: Priority = Priority.Low
)