package dev.martinsv.weatheralert.db.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.martinsv.weatheralert.app.db.AppDatabase
import dev.martinsv.weatheralert.db.dao.CityDao
import dev.martinsv.weatheralert.db.dao.ObservedCityDao
import dev.martinsv.weatheralert.db.repository.CityRepository
import dev.martinsv.weatheralert.db.repository.ObservedCityRepository
import javax.inject.Singleton

//todo rename
@Module
@InstallIn(SingletonComponent::class)
object SearchCityModule {

    @Singleton
    @Provides
    fun provideCityDao(appDatabase: AppDatabase): CityDao =
        appDatabase.cityDao()

    @Singleton
    @Provides
    fun provideCityRepository(cityDao: CityDao): CityRepository =
        CityRepository(cityDao)

//    @Singleton
//    @Provides
//    fun provideObservedCityDao(appDatabase: AppDatabase): ObservedCityDao =
//        appDatabase.observedCityDao()
//
//    @Singleton
//    @Provides
//    fun provideObservedCityRepository(observedCityDao: ObservedCityDao): ObservedCityRepository =
//        ObservedCityRepository(observedCityDao)

}