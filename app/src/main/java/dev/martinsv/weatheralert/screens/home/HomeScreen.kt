package dev.martinsv.weatheralert.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.martinsv.weatheralert.screens.CityItemModel
import dev.martinsv.weatheralert.screens.home.views.HomeCityList

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    //todo get by state from viewmodel
    var cities = remember {
        mutableStateOf(
            listOf(
                CityItemModel(id = 1, name = "Eindhoven", country = "Netherlands"),
                CityItemModel(id = 2, name = "Daugavpils", country = "Latvia")
            )
        )
    }
    HomeCityList(
        items = cities.value,
        navController = navController
    )

}