package dev.martinsv.weatheralert.screens.addcity.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.screens.CityItemModel
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme

@ExperimentalMaterialApi
@Composable
fun AddCityMain(
    cityName: String,
    onCityChange: (String) -> Unit,
    searchCityClick: () -> Unit,
    items: List<CityItemModel>,
    onCityClick: (CityItemModel) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Add city",
                    style = MaterialTheme.typography.h5
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .height(IntrinsicSize.Max)
            ) {
                CityInputInputText(
                    text = cityName,
                    onTextChange = onCityChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(4F)
                        .fillMaxHeight()
                )

                Button(
                    onClick = searchCityClick, modifier = Modifier
                        .weight(1F)
                        .padding(start = 8.dp, top = 8.dp)
                        .fillMaxHeight()
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search city")
                }
            }

            LazyColumn {
                items.forEach { cityItem ->
                    item {
                        AddCityItem(cityModel = cityItem, onCityClick = onCityClick)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CityInputInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = 1,
        label = { Text(text = "City") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        modifier = modifier
    )

}

@ExperimentalMaterialApi
@Preview
@Composable
fun AddCityMain_Preview() {
    WeatheralertTheme {
        AddCityMain(
            cityName = "Riga",
            onCityChange = {},
            items = listOf(
                CityItemModel(id = 1, name = "Eindhoven", country = "Netherlands"),
                CityItemModel(id = 2, name = "Daugavpils", country = "Latvia")
            ),
            searchCityClick = {},
            onCityClick = {}
        )
    }
}