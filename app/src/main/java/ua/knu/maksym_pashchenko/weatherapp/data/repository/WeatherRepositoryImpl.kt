package ua.knu.maksym_pashchenko.weatherapp.data.repository

import ua.knu.maksym_pashchenko.weatherapp.data.mapper.toDomain
import ua.knu.maksym_pashchenko.weatherapp.data.remote.api.WeatherApiService
import ua.knu.maksym_pashchenko.weatherapp.data.remote.datasource.WeatherRemoteDataSource
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
): WeatherRepository {
    override suspend fun getWeatherByCity(city: String): Weather {
        return remoteDataSource
            .getWeatherByCity(city)
            .toDomain()
    }
}