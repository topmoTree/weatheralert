package dev.martinsv.weatheralert.screens.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.ui.theme.WeatheralertTheme

@Composable
fun HomeEmptyCitiesList(addCityClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    imageVector = Icons.Filled.Info,
                    contentDescription = "No Items Found"
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                    text = "You don't have any observable cities.",
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = addCityClick,
                ) {
                    Text(text = "Add")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeEmptyCitiesList_Preview() {
    WeatheralertTheme {
        HomeEmptyCitiesList {}
    }
}