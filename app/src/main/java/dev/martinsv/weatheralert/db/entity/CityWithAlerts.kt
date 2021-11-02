package dev.martinsv.weatheralert.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CityWithAlerts(
    @Embedded val city: CityEntity,

    @Relation(
        parentColumn = "city_id",
        entityColumn = "city_id"
    ) val alerts: List<AlertEntity>
)