package dev.martinsv.weatheralert.api.repository

import dev.martinsv.weatheralert.api.api.WeatherApi
import dev.martinsv.weatheralert.api.api.data.AlertResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class WeatherApiRepository(
    private val weatherApi: WeatherApi
) {

    suspend fun getAlertsByCityId(cityId: Int): AlertResponse? =
        withContext(Dispatchers.IO) {
            weatherApi.getAlertsByCityId(cityId)
        }

    suspend fun getAlertsByCityAndCountryCode(city: String, countryCode: String): AlertResponse? =
        withContext(Dispatchers.IO) {
            weatherApi.getAlertsByCityAndCountryCode(city = city, countryCode = countryCode)
        }
}