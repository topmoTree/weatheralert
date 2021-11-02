package dev.martinsv.weatheralert.db.repository

import android.util.Log
import androidx.lifecycle.LiveData
import dev.martinsv.weatheralert.db.dao.CityDao
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.CityWithAlerts
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity
import kotlinx.coroutines.flow.Flow

class CityRepository(val cityDao: CityDao) {

    suspend fun searchCity(text: String) =
        cityDao.searchCityByText("$text%")

    fun getCitiesLd(observedCities: List<ObservedCityEntity>): LiveData<List<CityEntity>> {
        val ids = observedCities.map { it.cityId }
        return cityDao.getCitiesByIdsLd(ids)
    }

    suspend fun getCities(observedCities: List<ObservedCityEntity>): List<CityEntity> {
        val ids = observedCities.map { it.cityId }
        return cityDao.getCitiesByIds(ids)
    }

    fun citiesWithAlertsByIdsLd(observedCities: List<ObservedCityEntity>): LiveData<List<CityWithAlerts>> {
        val ids = observedCities.map { it.cityId }
        return cityDao.citiesWithAlertsByIdsLd(ids)
    }

    fun citiesWithAlertsByIdsFlow(observedCities: List<ObservedCityEntity>): Flow<List<CityWithAlerts>> {
        val ids = observedCities.map { it.cityId }
        return cityDao.citiesWithAlertsByIdsFlow(ids)
    }

    suspend fun getCityById(cityId: Int): CityEntity =
        cityDao.selectCityById(cityId)

    fun getCityByIdLd(cityId: Int): LiveData<CityEntity> =
        cityDao.getCityByIdLd(cityId)
}