package dev.martinsv.weatheralert.screens.home.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.CityWithAlerts
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme


//todo items get from state
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeCityList(
    modifier: Modifier = Modifier,
    items: List<CityWithAlerts>,
    onCityClick: (Int) -> Unit,
    addNewCityClick: () -> Unit,
    onDeleteCityClick: (Int) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            LazyColumn {
                items.forEach { cityItem ->
                    item {
                        HomeCityItem(
                            cityWithAlerts = cityItem,
                            onCityClick = { cityItem ->
                                onCityClick(cityItem.city.cityId)
                            },
                            onDeleteCityClick = { cityId ->
                                onDeleteCityClick(cityId)
                            }
                        )
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = addNewCityClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add city"
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview
@Composable
fun HomeCityList_Preview() {
    WeatheralertTheme {
        HomeCityList(
            items = listOf(
                CityWithAlerts(
                    CityEntity(
                        cityId = 567,
                        cityName = "Eindhoven",
                        stateCode = "State",
                        countryCode = "NL",
                        countryFull = "Netherlands",
                        lat = "123",
                        lon = "456"
                    ),
                    alerts = listOf(
                        AlertEntity(
                            id = 1,
                            cityId = 456,
                            regions = "Nord brabant",
                            endsUtc = "123",
                            effectiveUtc = "456",
                            effectiveLocal = "asd",
                            onsetUtc = "asd",
                            expiresLocal = "14.12",
                            expiresUtc = "45",
                            endsLocal = "asd",
                            uri = "asd",
                            onsetLocal = "45",
                            severity = "ttt",
                            title = "ttt",
                            description = "789"
                        )
                    )
                ),
                CityWithAlerts(
                    CityEntity(
                        cityId = 567,
                        cityName = "Riga",
                        stateCode = "State",
                        countryCode = "Lv",
                        countryFull = "Latvia",
                        lat = "123",
                        lon = "456"
                    ),
                    alerts = listOf(
                        AlertEntity(
                            id = 1,
                            cityId = 456,
                            regions = "Nord brabant",
                            endsUtc = "123",
                            effectiveUtc = "456",
                            effectiveLocal = "asd",
                            onsetUtc = "asd",
                            expiresLocal = "14.12",
                            expiresUtc = "45",
                            endsLocal = "asd",
                            uri = "asd",
                            onsetLocal = "45",
                            severity = "ttt",
                            title = "ttt",
                            description = "789"
                        )
                    )
                )
            ),
            onCityClick = {},
            addNewCityClick = {},
            onDeleteCityClick = {}
        )
    }
}