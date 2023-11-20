package sheridan.bajajku.assignment2.ui.common

import java.text.NumberFormat

fun formatCurrency(value: Double): String =
    NumberFormat.getCurrencyInstance().format(value)