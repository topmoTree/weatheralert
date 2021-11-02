package dev.martinsv.weatheralert.screens.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.martinsv.weatheralert.api.api.data.AlertResponse
import dev.martinsv.weatheralert.api.api.data.mapToAlertEntity
import dev.martinsv.weatheralert.api.repository.WeatherApiRepository
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity
import dev.martinsv.weatheralert.db.repository.AlertDbRepository
import dev.martinsv.weatheralert.db.repository.CityRepository
import dev.martinsv.weatheralert.db.repository.ObservedCityRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val observedCityRepository: ObservedCityRepository,
    private val weatherApiRepository: WeatherApiRepository,
    private val alertDbRepository: AlertDbRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            observedCityRepository.allObservedCitiesFlow().collect { observedCityEntities ->
                observedCities.value = observedCityEntities
            }
        }

        viewModelScope.launch {
            updateAlerts()
        }
    }

    private val observedCities = MutableLiveData<List<ObservedCityEntity>>(listOf())

    val citiesWithAlertsLd = Transformations.switchMap(observedCities) {
        cityRepository.citiesWithAlertsByIdsLd(it)
    }

    private val _deleteCityByIdLd = MutableLiveData<Int?>()
    val deleteCityById: LiveData<Int?> = _deleteCityByIdLd

    fun updateAlerts() {
        viewModelScope.launch {
            val observedCities = observedCityRepository.allObservedCities()

            observedCities.forEach { observedCity ->
                val city = cityRepository.getCityById(observedCity.cityId)
                val alertResponse = fetchAlertsFromApi(city)

                alertResponse?.let {
                    saveAlertResponseToDb(observedCity, it)
                }
            }
        }
    }

    //todo change name of method
    private suspend fun fetchAlertsFromApi(city: CityEntity): AlertResponse? =
        weatherApiRepository.getAlertsByCityAndCountryCode(
            city = city.cityName,
            countryCode = city.countryCode
        )

    private suspend fun saveAlertResponseToDb(
        observedCity: ObservedCityEntity,
        alertResponse: AlertResponse
    ) {
        val alertEntities = alertResponse.mapToAlertEntity(observedCity.cityId)
        alertDbRepository.addNewAlerts(observedCity.cityId, alertEntities)
    }

    fun showDeleteCityDialog(cityId: Int?) {
        _deleteCityByIdLd.value = cityId
    }

    fun dismissDeleteCityDialog() {
        _deleteCityByIdLd.value = null
    }

    fun deleteObservedCity(cityId: Int) {
        viewModelScope.launch {
            observedCityRepository.removeObservedCity(cityId)
            alertDbRepository.deleteAlertsByCityId(cityId)

            dismissDeleteCityDialog()
        }
    }
}