package dev.martinsv.weatheralert.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityId: Int,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name ="state_code")
    val stateCode: String,

    @ColumnInfo(name = "country_code")
    val countryCode: String,

    @ColumnInfo(name = "country_full")
    val countryFull: String,

    @ColumnInfo(name = "lat")
    val lat: String,

    @ColumnInfo(name = "lon")
    val lon: String,
)
