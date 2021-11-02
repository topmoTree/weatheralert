package dev.martinsv.weatheralert.screens.alert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.repository.AlertDbRepository
import dev.martinsv.weatheralert.db.repository.CityRepository
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val alertDbRepository: AlertDbRepository
) : ViewModel() {

    private val cityIdLd = MutableLiveData<Int>()

    fun setCityId(cityId: Int) {
        cityIdLd.value = cityId
    }

    val cityFromDbLd: LiveData<CityEntity> = Transformations.switchMap(cityIdLd) { cityId ->
        cityRepository.getCityByIdLd(cityId)
    }

    val alertsLd: LiveData<List<AlertEntity>> = Transformations.switchMap(cityIdLd) { cityId ->
        alertDbRepository.alertsByCityId(cityId)
    }
}
