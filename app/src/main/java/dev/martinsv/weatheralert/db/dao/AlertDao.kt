package dev.martinsv.weatheralert.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.CityEntity

@Dao
interface AlertDao {

    @Insert
    suspend fun insertAll(alerts: List<AlertEntity>)

    @Query("DELETE FROM alerts WHERE city_id = :cityId")
    suspend fun deleteAllAllertsByCityId(cityId: Int)

    @Query("SELECT * FROM alerts WHERE city_id = :cityId")
    fun alertsByCityId(cityId: Int): LiveData<List<AlertEntity>>
}