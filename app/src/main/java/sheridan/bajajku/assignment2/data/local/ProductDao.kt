package sheridan.bajajku.assignment2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import sheridan.bajajku.assignment2.domain.Priority

@Dao
interface ProductDao {

    @Query("SELECT * from products ORDER BY name ASC")
    fun getAllProductsStream(): Flow<List<LocalProduct>>

    @Query("SELECT * from products WHERE id = :id")
    fun getProductByIdStream(id: Int): Flow<LocalProduct?>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Product into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: LocalProduct)

    @Update
    suspend fun updateProduct(product: LocalProduct)

    @Query("UPDATE products SET name = :name WHERE id = :id")
    suspend fun updateProductNameById(id: Int, name: String)

    @Query("UPDATE products SET brand = :brand WHERE id = :id")
    suspend fun updateProductBrandById(id: Int, brand: String)

    @Query("UPDATE products SET priority = :priority WHERE id = :id")
    suspend fun updateProductPriorityById(id: Int, priority: Priority)

    @Delete
    suspend fun deleteProduct(product: LocalProduct)

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id: Int)
}
