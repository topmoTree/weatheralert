package dev.martinsv.weatheralert.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.martinsv.weatheralert.db.entity.CityEntity

@Dao
interface CityDao {

    @Insert
    suspend fun insertAll(cityEntities: List<CityEntity>)

    @Insert
    suspend fun insert(cityEntity: CityEntity)

    //todo should return pagination later
    @Query("SELECT * FROM cities WHERE city_name LIKE :text OR country_full LIKE :text OR country_code LIKE :text")
    fun findCityByText(text: String) : LiveData<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE city_name LIKE :text OR country_full LIKE :text OR country_code LIKE :text")
    suspend fun searchCityByText(text: String) : List<CityEntity>
}