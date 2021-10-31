package dev.martinsv.weatheralert.screens.addcity

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.repository.CityRepository
import dev.martinsv.weatheralert.db.repository.ObservedCityRepository
import dev.martinsv.weatheralert.screens.CityItemModel
import dev.martinsv.weatheralert.screens.mapToCityItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val cityRepository: CityRepository,
//    private val observedCityRepository: ObservedCityRepository
) :
    ViewModel() {
    private val cityTextMld = MutableLiveData("")
    val cityTextLd: LiveData<String> = cityTextMld

    val cities = MutableLiveData<List<CityItemModel>>(listOf())

    fun searchCity() {
        viewModelScope.launch {
            val searchText = cityTextMld.value
            //todo think about it later
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

    fun addCityToObserved(cityId: Int){
        Log.d("nolan", "add city $cityId")
//        viewModelScope.launch {
//            observedCityRepository.addCity(cityId = cityId)
//        }
    }
}