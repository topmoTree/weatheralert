package dev.martinsv.weatheralert.screens.addcity.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.screens.CityItemModel

@ExperimentalMaterialApi
@Composable
fun AddCityItem(
    cityModel: CityItemModel,
    onCityClick: (CityItemModel) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        onClick = { onCityClick(cityModel) }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${cityModel.name}, ${cityModel.country}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun AddCityItem_Preview() {
    AddCityItem(
        cityModel = CityItemModel(
            id = 1,
            name = "Eindhoven",
            country = "Netherlands"
        ),
        onCityClick = {}
    )
}