package dev.martinsv.weatheralert.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ObservedCityDao {

    @Insert
    suspend fun insert(observedCity: ObservedCityEntity)

    @Query("SELECT * FROM observed_city")
    fun selectAllLd() : LiveData<List<ObservedCityEntity>>

    @Query("SELECT * FROM observed_city")
    suspend fun selectAll() : List<ObservedCityEntity>

    @Query("SELECT * FROM observed_city")
    fun selectAllFlow() : Flow<List<ObservedCityEntity>>

    @Query("DELETE FROM observed_city WHERE city_id = :cityId")
    suspend fun removeObservedCityByCityId(cityId: Int)
}