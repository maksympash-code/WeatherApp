package ua.knu.maksym_pashchenko.weatherapp.data.repository

import ua.knu.maksym_pashchenko.weatherapp.data.mapper.toDomain
import ua.knu.maksym_pashchenko.weatherapp.data.remote.api.WeatherApiService
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService,
    private val apiKey: String
): WeatherRepository {
    override suspend fun getWeatherByCity(city: String): Weather {
        return apiService.getWeatherByCity(
            city = city,
            apiKey = apiKey
        ).toDomain()
    }

}