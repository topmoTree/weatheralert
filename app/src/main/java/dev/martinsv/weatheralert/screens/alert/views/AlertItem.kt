package dev.martinsv.weatheralert.screens.alert.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.db.entity.AlertEntity

@Composable
fun AlertItem(alert: AlertEntity) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = alert.title,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = alert.expiresLocal,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = alert.description,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun AlertItem_Preview() {
    AlertItem(
        alert = AlertEntity(
            id = 1,
            cityId = 456,
            regions = "Nord brabant",
            endsUtc = "123",
            effectiveUtc = "456",
            effectiveLocal = "asd",
            onsetUtc = "asd",
            expiresLocal = "14.12.2021",
            expiresUtc = "45",
            endsLocal = "asd",
            uri = "asd",
            onsetLocal = "45",
            severity = "3",
            title = "Hurricane",
            description = "Hurricanes are dangerous and can cause major damage because of storm surge, " +
                    "wind damage, rip currents and flooding. They can happen along any U.S. coast or " +
                    "in any territory in the Atlantic or Pacific oceans. " +
                    "Storm surge is historically the leading cause of hurricane-related deaths in the United States."
        )
    )
}