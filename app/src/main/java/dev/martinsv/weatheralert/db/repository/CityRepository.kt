package dev.martinsv.weatheralert.db.repository

import dev.martinsv.weatheralert.db.dao.CityDao

class CityRepository(val cityDao: CityDao) {

    fun findCityByText(text: String) =
        cityDao.findCityByText("%$text%")

    suspend fun searchCity(text: String) =
        cityDao.searchCityByText("%$text%")
}