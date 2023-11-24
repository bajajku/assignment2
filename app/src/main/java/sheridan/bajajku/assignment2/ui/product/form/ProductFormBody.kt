package sheridan.bajajku.assignment2.ui.product.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import sheridan.bajajku.assignment2.R
import sheridan.bajajku.assignment2.domain.Priority

@Composable
fun ProductFormBody(
    productFormUiState: ProductFormUiState,
    onNameChange: (String) -> Unit,
    onBrandChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onPrioritySelected: (Priority) -> Unit,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        ProductForm(
            productFormModel = productFormUiState.productFormModel,
            onNameChange = onNameChange,
            onBrandChange = onBrandChange,
            onQuantityChange = onQuantityChange,
            onPrioritySelected = onPrioritySelected,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = productFormUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_action))
        }
    }
}