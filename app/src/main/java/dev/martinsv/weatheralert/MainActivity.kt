package dev.martinsv.weatheralert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.martinsv.weatheralert.screens.addcity.AddCityScreen
import dev.martinsv.weatheralert.screens.addcity.AddCityViewModel
import dev.martinsv.weatheralert.screens.home.HomeScreen
import dev.martinsv.weatheralert.screens.home.HomeViewModel
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatheralertTheme {
                val navController = rememberNavController()

                Scaffold() {
                    NavigationComponent(navController = navController)
                }
            }
        }

//        lifecycleScope.launchWhenResumed {
//            val weatherAlertResponse = weatherApi.getAlertsByCityAndCountryCode("Palermo", "IT")
//            Log.d("nolan", weatherAlertResponse.alerts[0].title)
//
//            val cities = cityDao.findCityBySearchText("daugavpils")
//            Log.d("nolan", cities[0]?.city_name)
//        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable("alertdetails") {
            //todo later
        }
        composable("addcity"){
            val addCityViewModel = hiltViewModel<AddCityViewModel>()

            AddCityScreen(
                navController = navController,
                addCityViewModel = addCityViewModel
            )
        }
    }
}
