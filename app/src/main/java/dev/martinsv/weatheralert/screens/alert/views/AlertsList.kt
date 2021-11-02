package dev.martinsv.weatheralert.screens.alert.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.martinsv.weatheralert.db.entity.AlertEntity

@Composable
fun AlertsList(
    modifier: Modifier = Modifier,
    items: List<AlertEntity>,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            LazyColumn {
                items.forEach { alert ->
                    item {
                        AlertItem(alert)
                    }
                }
            }
        }
    }

}