package dev.martinsv.weatheralert.api.api

import AlertResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v2.0/alerts")
    suspend fun getAlertsByCityAndCountryCode(
        @Query("city") city: String,
        @Query("country") countryCode: String,
    ) : AlertResponse
}