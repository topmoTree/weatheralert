package dev.martinsv.weatheralert.screens.addcity

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.martinsv.weatheralert.screens.addcity.views.AddCityMain
import dev.martinsv.weatheralert.screens.addcity.views.AddCityToObservedDialog

@ExperimentalMaterialApi
@Composable
fun AddCityScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    addCityViewModel: AddCityViewModel,
    setTopBarTitle: (String) -> Unit,
    isNavigationIconVisible: (Boolean) -> Unit
) {
    isNavigationIconVisible(true)
    setTopBarTitle("Add city")

    SetupMainContentView(addCityViewModel)
    SetupAddCityDialog(addCityViewModel)
    SetupNavigationToPreviousScreen(addCityViewModel, navController)
}

@ExperimentalMaterialApi
@Composable
private fun SetupMainContentView(addCityViewModel: AddCityViewModel) {
    val city = addCityViewModel.cityTextLd.observeAsState("")
    val cities = addCityViewModel.cities.observeAsState(emptyList())

    AddCityMain(
        cityName = city.value,
        onCityChange = { city -> addCityViewModel.setCityName(city) },
        searchCityClick = { addCityViewModel.searchCity() },
        items = cities.value,
        onCityClick = { cityId -> addCityViewModel.showAddCityDialog(cityId) }
    )
}

@Composable
private fun SetupAddCityDialog(addCityViewModel: AddCityViewModel) {
    val addCityById by addCityViewModel.addCityByIdLd.observeAsState()

    addCityById?.let { cityId ->
        AddCityToObservedDialog(
            cityId = cityId,
            dismissDialog = { addCityViewModel.dismissAddCityDialog() },
            addCityToObserved = { cityId -> addCityViewModel.addCityToObserved(cityId) }
        )
    }
}

@Composable
private fun SetupNavigationToPreviousScreen(
    addCityViewModel: AddCityViewModel,
    navController: NavController
) {
    val isPopBackStack by addCityViewModel.navigateToPreviousScreen.observeAsState(false)
    if (isPopBackStack) navController.navigateUp()
}