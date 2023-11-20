package sheridan.bajajku.assignment2.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment2.data.local.LocalProduct
import sheridan.bajajku.assignment2.data.local.ProductDao
import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.domain.Product
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class LocalProductsRepository(
    private val productDao: ProductDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : ProductsRepository {

    @Inject
    constructor(productDao: ProductDao) : this(productDao, GlobalScope, Dispatchers.IO)

    override fun getAllProductsStream(): Flow<List<Product>> =
        productDao.getAllProductsStream()
            .map{ list -> list.map { localProduct ->  localProduct.toProduct() }}
            .flowOn(dispatcher)

    override fun getProductByIdStream(id: Int): Flow<Product?> =
        productDao.getProductByIdStream(id)
            .map { localProduct -> localProduct?.toProduct() }
            .flowOn(dispatcher)

    override suspend fun insertProduct(product: Product) {
        externalScope.launch(dispatcher) { productDao.insertProduct(product.toLocalProduct()) }.join()
    }

    override suspend fun deleteProduct(product: Product) {
        externalScope.launch(dispatcher) { productDao.deleteProduct(product.toLocalProduct()) }.join()
    }

    override suspend fun deleteProductById(id: Int){
        externalScope.launch(dispatcher) { productDao.deleteProductById(id) }.join()
    }

    override suspend fun updateProduct(product: Product) {
        externalScope.launch(dispatcher) { productDao.updateProduct(product.toLocalProduct()) }.join()
    }

    override suspend fun updateProductNameById(id: Int, name: String) {
        externalScope.launch(dispatcher) { productDao.updateProductNameById(id, name) }.join()
    }

    override suspend fun updateProductBrandById(id: Int, brand: String) {
        externalScope.launch(dispatcher) { productDao.updateProductBrandById(id, brand) }.join()
    }
    override suspend fun updateProductPriorityById(id: Int, priority: Priority) {
        externalScope.launch(dispatcher) { productDao.updateProductPriorityById(id, priority) }.join()
    }
}

fun LocalProduct.toProduct(): Product = Product(
    id = this.id,
    name = this.name,
    brand = this.brand,
    priority = this.priority
)

fun Product.toLocalProduct(): LocalProduct = LocalProduct(
    id = this.id,
    name = this.name,
    brand = this.brand,
    priority = this.priority
)
