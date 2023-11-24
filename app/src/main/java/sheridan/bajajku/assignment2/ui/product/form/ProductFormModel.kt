package sheridan.bajajku.assignment2.ui.product.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import sheridan.bajajku.assignment2.R
import sheridan.bajajku.assignment2.domain.Priority
import sheridan.bajajku.assignment2.ui.model.ProductFormModel


@Composable
fun ProductForm(
    productFormModel: ProductFormModel,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit,
    onBrandChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onPrioritySelected: (Priority) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = productFormModel.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(R.string.product_name_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4527A0),
                unfocusedBorderColor = Color(0xFF512DA8),
                disabledBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF4527A0),
                unfocusedLabelColor = Color(0xFF512DA8),
                disabledLabelColor = Color.Gray,
                cursorColor = Color(0xFF4527A0),
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = productFormModel.brand,
            onValueChange = onBrandChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(stringResource(R.string.product_brand)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00695C),
                unfocusedBorderColor = Color(0xFF00796B),
                disabledBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF00695C),
                unfocusedLabelColor = Color(0xFF00796B),
                disabledLabelColor = Color.Gray,
                cursorColor = Color(0xFF00695C),
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = productFormModel.quantity,
            onValueChange = onQuantityChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.quantity_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBF360C),
                unfocusedBorderColor = Color(0xFFD84315),
                disabledBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFFBF360C),
                unfocusedLabelColor = Color(0xFFD84315),
                disabledLabelColor = Color.Gray,
                cursorColor = Color(0xFFBF360C),
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Text(text = stringResource(R.string.select_priority), fontWeight = FontWeight.Bold, color = Color(0xFF795548))

        Priority.values().forEach { priority ->
            PriorityRadioButton(
                priority = priority,
                selectedPriority = productFormModel.priority,
                onPrioritySelected = { onPrioritySelected(it) }
            )
        }

        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
                color = Color(0xFF004D40)
            )
        }
    }}

    @Composable
    fun PriorityRadioButton(
        priority: Priority,
        selectedPriority: Priority,
        onPrioritySelected: (Priority) -> Unit
    ) {
        Row(
            modifier = Modifier
                .selectable(
                    selected = priority == selectedPriority,
                    onClick = { onPrioritySelected(priority) }
                )
        ) {
            RadioButton(
                selected = priority == selectedPriority,
                onClick = { onPrioritySelected(priority) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF795548),
                    unselectedColor = Color(0xFF8D6E63),
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = priority.name,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                color = Color(0xFF795548)
            )
        }
    }
