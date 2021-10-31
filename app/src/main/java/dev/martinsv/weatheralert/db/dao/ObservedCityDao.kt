package dev.martinsv.weatheralert.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.ObservedCity

@Dao
interface ObservedCityDao {

    @Insert
    suspend fun insert(observedCity: ObservedCity)

    @Query("SELECT * FROM observed_city")
    fun selectAll() : LiveData<List<ObservedCity>>
}