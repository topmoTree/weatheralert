package dev.martinsv.weatheralert.db.repository

import androidx.lifecycle.LiveData
import dev.martinsv.weatheralert.api.repository.WeatherApiRepository
import dev.martinsv.weatheralert.db.dao.ObservedCityDao
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity
import kotlinx.coroutines.flow.Flow

class ObservedCityRepository(
    private val observedCityDao: ObservedCityDao
) {

    suspend fun addNewCity(cityId: Int) {
        observedCityDao.insert(ObservedCityEntity(cityId = cityId))
    }

    suspend fun allObservedCities(): List<ObservedCityEntity> =
        observedCityDao.selectAll()

    fun allObservedCitiesFlow(): Flow<List<ObservedCityEntity>> =
        observedCityDao.selectAllFlow()

    suspend fun removeObservedCity(cityId: Int) {
        observedCityDao.removeObservedCityByCityId(cityId)
    }
}