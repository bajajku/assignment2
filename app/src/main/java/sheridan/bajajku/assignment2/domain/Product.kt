package sheridan.bajajku.assignment2.domain

data class Product(
    val id: Int = 0,
    val name: String = "No Name",
    val brand: String = "",
    val priority: Priority = Priority.Low
)