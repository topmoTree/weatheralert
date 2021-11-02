package dev.martinsv.weatheralert.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.martinsv.weatheralert.db.AppDatabase.Companion.DB_VERSION
import dev.martinsv.weatheralert.db.dao.AlertDao
import dev.martinsv.weatheralert.db.dao.CityDao
import dev.martinsv.weatheralert.db.dao.ObservedCityDao
import dev.martinsv.weatheralert.db.entity.AlertEntity
import dev.martinsv.weatheralert.db.entity.CityEntity
import dev.martinsv.weatheralert.db.entity.ObservedCityEntity

@Database(
    entities = [CityEntity::class, ObservedCityEntity::class, AlertEntity::class],
    version = DB_VERSION,
    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun observedCityDao(): ObservedCityDao
    abstract fun alertDao(): AlertDao

    companion object {
        const val DB_NAME = "weather.db"
        const val DB_VERSION = 1
    }
}