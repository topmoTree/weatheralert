package dev.martinsv.weatheralert.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val city_id: Int,

    @ColumnInfo(name = "city_name")
    val city_name: String,

    @ColumnInfo(name = "country_code")
    val country_code: String,

    @ColumnInfo(name = "country_full")
    val country_full: String,

    @ColumnInfo(name = "lat")
    val lat: String,

    @ColumnInfo(name = "lon")
    val lon: String,
)
