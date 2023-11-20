package sheridan.bajajku.assignment2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import sheridan.bajajku.assignment2.domain.Priority

@Entity(tableName = "products")
data class LocalProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String ,
    val brand: String,
    val priority: Priority
)
