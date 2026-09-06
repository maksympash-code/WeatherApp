package ua.knu.maksym_pashchenko.weatherapp.di

import android.content.Context
import androidx.room.Room
import ua.knu.maksym_pashchenko.weatherapp.BuildConfig
import ua.knu.maksym_pashchenko.weatherapp.data.local.database.WeatherDatabase
import ua.knu.maksym_pashchenko.weatherapp.data.remote.RetrofitInstance
import ua.knu.maksym_pashchenko.weatherapp.data.remote.datasource.WeatherRemoteDataSource
import ua.knu.maksym_pashchenko.weatherapp.data.repository.FavoriteCityRepositoryImpl
import ua.knu.maksym_pashchenko.weatherapp.data.repository.RecentCityRepositoryImpl
import ua.knu.maksym_pashchenko.weatherapp.data.repository.WeatherRepositoryImpl
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.RecentCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class AppContainer(
    context: Context
) {
    private val database: WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()

    private val weatherRemoteDataSource =
        WeatherRemoteDataSource(
            api = RetrofitInstance.weatherApiService,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        )

    val weatherRepository: WeatherRepository =
        WeatherRepositoryImpl(
            remoteDataSource = weatherRemoteDataSource
        )

    val favoriteCityRepository: FavoriteCityRepository =
        FavoriteCityRepositoryImpl(
            dao = database.favoriteCityDao()
        )

    val recentCityRepository: RecentCityRepository =
        RecentCityRepositoryImpl(
            dao = database.recentCitiesDao()
        )
}