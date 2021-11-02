package dev.martinsv.weatheralert.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.martinsv.weatheralert.api.ApiData
import dev.martinsv.weatheralert.api.api.WeatherApi
import dev.martinsv.weatheralert.api.repository.WeatherApiRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideApiKeyInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("key", ApiData.API_KEY).build()
            val newRequest = request.newBuilder().url(url).build()
            return@Interceptor chain.proceed(newRequest)
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiData.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherApiRepository(weatherApi: WeatherApi) =
        WeatherApiRepository(weatherApi = weatherApi)
}