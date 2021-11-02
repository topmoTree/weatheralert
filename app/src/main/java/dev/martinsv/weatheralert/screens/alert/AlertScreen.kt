package dev.martinsv.weatheralert.screens.alert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.martinsv.weatheralert.screens.alert.views.AlertsList
import dev.martinsv.weatheralert.screens.alert.views.AlertsListIsEmpty

@Composable
fun AlertDetailsScreen(
    cityId: Int,
    modifier: Modifier = Modifier,
    navController: NavController,
    alertViewModel: AlertViewModel,
    setTopBarTitle: (String) -> Unit,
    isNavigationIconVisible: (Boolean) -> Unit
) {
    alertViewModel.setCityId(cityId)
    SetupTopBar(isNavigationIconVisible, alertViewModel, setTopBarTitle)

    val alerts by alertViewModel.alertsLd.observeAsState(emptyList())

    if (alerts.isEmpty()) {
        AlertsListIsEmpty(
            toPreviousScreenClick = { navController.popBackStack() }
        )
    } else {
        AlertsList(items = alerts)
    }
}

@Composable
private fun SetupTopBar(
    isNavigationIconVisible: (Boolean) -> Unit,
    alertViewModel: AlertViewModel,
    setTopBarTitle: (String) -> Unit
) {
    isNavigationIconVisible(true)
    val city by alertViewModel.cityFromDbLd.observeAsState()
    city?.let {
        setTopBarTitle("${it.cityName}, ${it.countryFull}")
    }
}