package dev.martinsv.weatheralert.screens.addcity.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddCityToObservedDialog(
    modifier: Modifier = Modifier,
    cityId: Int,
    dismissDialog: () -> Unit,
    addCityToObserved: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = dismissDialog,
        title = { Text(text = "Add?") },
        text = { Text(text = "Add city to observed?") },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = dismissDialog
                ) {
                    Text("Cancel")
                }

                TextButton(
                    onClick = { addCityToObserved(cityId) }
                ) {
                    Text("Yes")
                }
            }
        }
    )
}

@Preview
@Composable
fun AddCityToObserved_Preview() {
    AddCityToObservedDialog(
        cityId = 2,
        dismissDialog = {},
        addCityToObserved = {}
    )
}