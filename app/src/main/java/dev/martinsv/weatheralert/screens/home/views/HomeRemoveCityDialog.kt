package dev.martinsv.weatheralert.screens.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@ExperimentalComposeUiApi
@Composable
fun HomeRemoveCityDialog(
    modifier: Modifier = Modifier,
    cityId: Int,
    dismissDialog: () -> Unit,
    removeObservedCity: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = dismissDialog,
        title = { Text(text = "Remove?") },
        text = { Text(text = "Do you want to remove this city?") },
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
                    onClick = {
                        removeObservedCity(cityId)
                    }
                ) {
                    Text("Yes")
                }
            }
        }
    )
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun HomeRemoveCityDialog_Preview() {
    HomeRemoveCityDialog(
        cityId = 2,
        dismissDialog = {},
        removeObservedCity = {}
    )
}
