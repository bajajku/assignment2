package sheridan.bajajku.assignment2.domain

data class Product(
    val name: String = "No Name",
    val brand: String = "",
    val priority: Priority
)