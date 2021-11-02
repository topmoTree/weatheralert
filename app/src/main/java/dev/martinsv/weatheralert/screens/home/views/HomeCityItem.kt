package dev.martinsv.weatheralert.screens.home.views

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.CityWithAlerts

@ExperimentalMaterialApi
@Composable
fun HomeCityItem(
    cityWithAlerts: CityWithAlerts,
    onCityClick: (CityWithAlerts) -> Unit,
    onDeleteCityClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
        onClick = {
            onCityClick(cityWithAlerts)
        }
    ) {
        val hasAlerts = cityWithAlerts.alerts.isNotEmpty()

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = if (hasAlerts) Icons.Outlined.Error else Icons.Filled.LocationOn,
                contentDescription = "City icon",
                tint = if (hasAlerts) MaterialTheme.colors.primary else LocalContentColor.current.copy(
                    alpha = LocalContentAlpha.current
                )
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "${cityWithAlerts.city.cityName}, ${cityWithAlerts.city.countryFull}",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                )
                Text(
                    text = "Alerts: ${cityWithAlerts.alerts.size}",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1
                )
            }

            IconButton(onClick = {
                onDeleteCityClick(cityWithAlerts.city.cityId)
            }) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "City icon"
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun HomeCityItem_Preview() {
    HomeCityItem(
        cityWithAlerts = CityWithAlerts(
            CityEntity(
                cityId = 567,
                cityName = "Eindhoven",
                stateCode = "State",
                countryCode = "NL",
                countryFull = "Netherlands",
                lat = "123",
                lon = "456"
            ),
            alerts = listOf(
                AlertEntity(
                    id = 1,
                    cityId = 456,
                    regions = "Nord brabant",
                    endsUtc = "123",
                    effectiveUtc = "456",
                    effectiveLocal = "asd",
                    onsetUtc = "asd",
                    expiresLocal = "14.12",
                    expiresUtc = "45",
                    endsLocal = "asd",
                    uri = "asd",
                    onsetLocal = "45",
                    severity = "ttt",
                    title = "ttt",
                    description = "789"
                )
            )
        ),
        onCityClick = {},
        onDeleteCityClick = {}
    )
}