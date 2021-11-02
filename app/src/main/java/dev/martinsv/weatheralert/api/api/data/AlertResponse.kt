package dev.martinsv.weatheralert.api.api.data

import com.google.gson.annotations.SerializedName
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity

data class AlertResponse(

    @SerializedName("country_code") val country_code: String,
    @SerializedName("lon") val lon: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("alerts") val alerts: List<Alerts>,
    @SerializedName("city_name") val city_name: String,
    @SerializedName("state_code") val state_code: String
)

fun AlertResponse.mapToAlertEntity(cityId: Int): List<AlertEntity> =
    this.alerts.map {
        AlertEntity(
            cityId = cityId,
            regions = it.regions.toString(),
            endsUtc = it.ends_utc,
            effectiveLocal = it.effective_local,
            onsetUtc = it.onset_utc,
            expiresLocal = it.expires_local,
            expiresUtc = it.expires_utc,
            endsLocal = it.ends_local,
            uri = it.uri,
            onsetLocal = it.onset_local,
            effectiveUtc = it.effective_utc,
            severity = it.severity,
            title = it.title,
            description = it.description
        )
    }

