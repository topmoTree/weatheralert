package dev.martinsv.weatheralert.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.CityWithAlerts
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert
    suspend fun insertAll(cityEntities: List<CityEntity>)

    @Insert
    suspend fun insert(cityEntity: CityEntity)

    @Query("SELECT * FROM cities WHERE city_id = :cityId LIMIT 1")
    suspend fun selectCityById(cityId: Int) : CityEntity

    @Query("SELECT * FROM cities WHERE city_id = :cityId LIMIT 1")
    fun getCityByIdLd(cityId: Int) : LiveData<CityEntity>

    @Query("SELECT * FROM cities WHERE city_name LIKE :text")
    suspend fun searchCityByText(text: String) : List<CityEntity>

    @Query("SELECT * FROM cities WHERE city_id IN (:ids)")
    fun getCitiesByIdsLd(ids: List<Int>): LiveData<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE city_id IN (:ids)")
    suspend fun getCitiesByIds(ids: List<Int>): List<CityEntity>

    @Transaction
    @Query("SELECT * FROM cities WHERE city_id IN (:ids)")
    fun citiesWithAlertsByIdsLd(ids: List<Int>): LiveData<List<CityWithAlerts>>

    @Transaction
    @Query("SELECT * FROM cities WHERE city_id IN (:ids)")
    fun citiesWithAlertsByIdsFlow(ids: List<Int>): Flow<List<CityWithAlerts>>
}