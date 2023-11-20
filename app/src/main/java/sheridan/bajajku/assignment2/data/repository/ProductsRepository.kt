package sheridan.bajajku.assignment2.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product

interface ProductsRepository {
    /**
     * Retrieve all the products from the the given data source.
     */
    fun getAllProductsStream(): Flow<List<Product>>

    /**
     * Retrieve an product from the given data source that matches with the [id].
     */
    fun getProductByIdStream(id: Int): Flow<Product?>

    /**
     * Insert product in the data source
     */
    suspend fun insertProduct(product: Product)

    /**
     * Delete product from the data source
     */
    suspend fun deleteProduct(product: Product)

    suspend fun deleteProductById(id: Int)

    /**
     * Update product in the data source
     */
    suspend fun updateProduct(product: Product)

    suspend fun updateProductNameById(id: Int, name: String)

    suspend fun updateProductBrandById(id: Int, brand: String)

    suspend fun updateProductPriorityById(id: Int, priority: Priority)

    suspend fun updateProductQuantityById(id: Int, quantity: Int)

}
