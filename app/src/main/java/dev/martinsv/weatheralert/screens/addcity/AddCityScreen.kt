package dev.martinsv.weatheralert.screens.addcity

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.martinsv.weatheralert.screens.addcity.views.AddCityMain

@ExperimentalMaterialApi
@Composable
fun AddCityScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    addCityViewModel: AddCityViewModel
) {
    val city = addCityViewModel.cityTextLd.observeAsState("")
    val cities = addCityViewModel.cities.observeAsState(emptyList())

    AddCityMain(
        cityName = city.value,
        onCityChange = { city -> addCityViewModel.setCityName(city) },
        searchCityClick = { addCityViewModel.searchCity() },
        items = cities.value,
        onCityClick = { cityModel -> addCityViewModel.addCityToObserved(cityModel.id) }
    )
}