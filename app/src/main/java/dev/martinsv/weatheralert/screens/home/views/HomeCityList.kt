package dev.martinsv.weatheralert.screens.home.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.martinsv.weatheralert.screens.CityItemModel
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme


//todo items get from state
@ExperimentalFoundationApi
@Composable
fun HomeCityList(
    modifier: Modifier = Modifier,
    items: List<CityItemModel>,
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box {
            LazyColumn {
                stickyHeader {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Weather alerts",
                            style = MaterialTheme.typography.h5
                        )
                    }
                }

                items.forEach { cityItem ->
                    item {
                        //todo pass onCityClick

                        HomeCityItem(
                            cityModel = cityItem,
                            onCityClick = { cityItem ->
                                Log.d(
                                    "nolan",
                                    "on city click id ${cityItem.id}"
                                )
                            }
                        )
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { navController.navigate("addcity") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add city"
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeCityList_Preview() {
    WeatheralertTheme {
        HomeCityList(
            items = listOf(
                CityItemModel(id = 1, name = "Eindhoven", country = "Netherlands"),
                CityItemModel(id = 2, name = "Daugavpils", country = "Latvia")
            ),
            navController = rememberNavController()
        )
    }
}