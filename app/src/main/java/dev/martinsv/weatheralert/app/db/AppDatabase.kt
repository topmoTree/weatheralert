package dev.martinsv.weatheralert.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.martinsv.weatheralert.db.dao.CityDao
import dev.martinsv.weatheralert.db.dao.ObservedCityDao
import dev.martinsv.weatheralert.db.entity.CityEntity

//, ObservedCityDao::class
@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
//    abstract fun observedCityDao() : ObservedCityDao

    companion object {
        const val DB_NAME = "weather.db"
        const val DB_VERSION = 1
    }
}