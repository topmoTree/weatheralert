package dev.martinsv.weatheralert.screens.addcity

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.martinsv.weatheralert.api.api.data.mapToAlertEntity
import dev.martinsv.weatheralert.api.repository.WeatherApiRepository
import dev.martinsv.weatheralert.db.repository.AlertDbRepository
import dev.martinsv.weatheralert.db.repository.CityRepository
import dev.martinsv.weatheralert.db.repository.ObservedCityRepository
import dev.martinsv.weatheralert.screens.CityItemModel
import dev.martinsv.weatheralert.screens.mapToCityItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val observedCityRepository: ObservedCityRepository,
    private val weatherApiRepository: WeatherApiRepository,
    private val alertDbRepository: AlertDbRepository
) :
    ViewModel() {

    private val cityTextMld = MutableLiveData("")
    val cityTextLd: LiveData<String> = cityTextMld

    val cities = MutableLiveData<List<CityItemModel>>(listOf())

    private val _addCityByIdLd = MutableLiveData<Int?>()
    val addCityByIdLd: LiveData<Int?> = _addCityByIdLd

    private val _navigateToPreviousScreen = MutableLiveData(false)
    val navigateToPreviousScreen: LiveData<Boolean> = _navigateToPreviousScreen

    fun searchCity() {
        viewModelScope.launch {
            val searchText = cityTextMld.value
            if (searchText.isNullOrBlank()) return@launch

            val citiesFromDb = cityRepository.searchCity(searchText)
            Log.d("nolan", "cities size: ${citiesFromDb.size}")
            cities.postValue(
                citiesFromDb.map { it.mapToCityItem() }
            )
        }
    }

    fun setCityName(city: String) {
        cityTextMld.value = city
    }

    fun addCityToObserved(cityId: Int) {
        viewModelScope.launch {
            try {
                observedCityRepository.addNewCity(cityId = cityId)
            } catch (e: SQLiteConstraintException) {
                Log.d("nolan", "Already exists")
                //todo show user that this city is added
            }

            obtainAndSaveAlerts(cityId)

            dismissAddCityDialog()
            _navigateToPreviousScreen.value = true
        }
    }

    //todo
    private suspend fun obtainAndSaveAlerts(cityId: Int) {
        val city = cityRepository.getCityById(cityId)

        val alertResponse = weatherApiRepository.getAlertsByCityAndCountryCode(
            city = city.cityName,
            countryCode = city.countryCode
        )

        alertResponse?.let {
            val alertEntities = it.mapToAlertEntity(cityId)
            alertDbRepository.addNewAlerts(cityId, alertEntities)
        }

    }

    fun showAddCityDialog(cityId: Int?) {
        _addCityByIdLd.value = cityId
    }

    fun dismissAddCityDialog() {
        _addCityByIdLd.value = null
    }
}