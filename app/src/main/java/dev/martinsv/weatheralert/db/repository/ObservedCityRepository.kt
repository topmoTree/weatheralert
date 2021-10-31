package dev.martinsv.weatheralert.db.repository

import androidx.lifecycle.LiveData
import dev.martinsv.weatheralert.db.dao.ObservedCityDao
import dev.martinsv.weatheralert.db.entity.ObservedCity

class ObservedCityRepository(private val observedCityDao: ObservedCityDao) {

    suspend fun addCity(cityId: Int){
        observedCityDao.insert(ObservedCity(city_id = cityId))
    }

    fun allObservedCities() : LiveData<List<ObservedCity>> =
        observedCityDao.selectAll()
}