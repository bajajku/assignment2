package sheridan.bajajku.assignment2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment2.data.repository.ProductsRepository
import sheridan.bajajku.assignment2.ui.model.ListProductModel
import sheridan.bajajku.assignment2.ui.model.toListProductModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    /*
     * Holds home ui state. The list of products are retrieved from [ProductsRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        productsRepository.getAllProductsStream()
            .map { list -> HomeUiState(list.map { product -> product.toListProductModel() }) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun toggleSelect(product: ListProductModel) {
        viewModelScope.launch {
            productsRepository.updateProductSelectedById(product.id, !product.selected)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
