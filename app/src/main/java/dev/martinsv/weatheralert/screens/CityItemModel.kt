package dev.martinsv.weatheralert.screens

import dev.martinsv.weatheralert.db.entity.CityEntity

data class CityItemModel(val id: Int, val name: String, val country: String)

fun CityEntity.mapToCityItem() =
    CityItemModel(
        id = this.cityId,
        name = this.cityName,
        country = this.countryFull
    )