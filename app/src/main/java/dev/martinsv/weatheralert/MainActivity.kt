package dev.martinsv.weatheralert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.martinsv.weatheralert.screens.addcity.AddCityScreen
import dev.martinsv.weatheralert.screens.addcity.AddCityViewModel
import dev.martinsv.weatheralert.screens.alert.AlertDetailsScreen
import dev.martinsv.weatheralert.screens.alert.AlertViewModel
import dev.martinsv.weatheralert.screens.home.HomeScreen
import dev.martinsv.weatheralert.screens.home.HomeViewModel
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatheralertTheme {
                val navController = rememberNavController()
                val topBarTitle = remember { mutableStateOf("") }
                val isNavigationIconVisible = remember {
                    mutableStateOf(false)
                }

                Scaffold(
                    topBar = {
                        CustomTopBar(topBarTitle, isNavigationIconVisible, navController)
                    }
                ) {
                    NavigationComponent(
                        navController = navController,
                        setTopBarTitle = { title -> topBarTitle.value = title },
                        isNavigationIconVisible = { visible ->
                            isNavigationIconVisible.value = visible
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomTopBar(
    topBarTitle: MutableState<String>,
    isNavigationIconVisible: MutableState<Boolean>,
    navController: NavHostController
) {
    TopAppBar(
        title = { Text(text = topBarTitle.value) },
        navigationIcon = if (isNavigationIconVisible.value) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate to previous screen"
                    )
                }
            }
        } else null
    )
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    setTopBarTitle: (String) -> Unit,
    isNavigationIconVisible: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                setTopBarTitle = setTopBarTitle,
                isNavigationIconVisible = isNavigationIconVisible
            )
        }
        composable(
            "alertdetails/{cityId}",
            arguments = listOf(navArgument("cityId") { type = NavType.IntType })
        ) { backStackEntry ->
            val alertDetailsViewModel = hiltViewModel<AlertViewModel>()
            val cityId = backStackEntry.arguments?.getInt("cityId")
            require(cityId != null) { "CityId Argument is null" }

            AlertDetailsScreen(
                cityId = cityId,
                navController = navController,
                alertViewModel = alertDetailsViewModel,
                setTopBarTitle = setTopBarTitle,
                isNavigationIconVisible = isNavigationIconVisible
            )
        }

        composable("addcity") {
            val addCityViewModel = hiltViewModel<AddCityViewModel>()

            AddCityScreen(
                navController = navController,
                addCityViewModel = addCityViewModel,
                setTopBarTitle = setTopBarTitle,
                isNavigationIconVisible = isNavigationIconVisible
            )
        }
    }
}
