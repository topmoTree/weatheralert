package dev.martinsv.weatheralert.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.martinsv.weatheralert.screens.home.views.*
import dev.martinsv.weatheralert.screens.home.views.HomeRemoveCityDialog
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel,
    setTopBarTitle: (String) -> Unit,
    isNavigationIconVisible: (Boolean) -> Unit
) {
    isNavigationIconVisible(false)
    setTopBarTitle("Weather alerts")

    SetupDeleteObservedCityDialog(homeViewModel)
    SetupBaseContent(homeViewModel, navController)
}

@ExperimentalComposeUiApi
@Composable
private fun SetupDeleteObservedCityDialog(homeViewModel: HomeViewModel) {
    val deleteCityById by homeViewModel.deleteCityById.observeAsState()

    deleteCityById?.let { cityId ->
        HomeRemoveCityDialog(
            cityId = cityId,
            dismissDialog = { homeViewModel.dismissDeleteCityDialog() },
            removeObservedCity = { cityId -> homeViewModel.deleteObservedCity(cityId) }
        )
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun SetupBaseContent(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val citiesWithAlerts by homeViewModel.citiesWithAlertsLd.observeAsState(null)

    if (citiesWithAlerts == null) {
        HomeIsLoading()
    } else {
        citiesWithAlerts?.let {
            if (it.isEmpty()) {
                HomeEmptyCitiesList(
                    addCityClick = { navController.navigate("addcity") }
                )
            } else {
                HomeCityList(
                    items = it,
                    onCityClick = { cityId -> navController.navigate("alertdetails/$cityId") },
                    addNewCityClick = { navController.navigate("addcity") },
                    onDeleteCityClick = { cityId ->
                        homeViewModel.showDeleteCityDialog(cityId)
                    }
                )
            }
        }
    }
}
