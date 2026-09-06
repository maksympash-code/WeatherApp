package ua.knu.maksym_pashchenko.weatherapp.data.remote.datasource

import ua.knu.maksym_pashchenko.weatherapp.data.remote.api.WeatherApiService
import ua.knu.maksym_pashchenko.weatherapp.data.remote.dto.WeatherDto

class WeatherRemoteDataSource(
    private val api: WeatherApiService,
    private val apiKey: String
) {

    suspend fun getWeatherByCity(city: String): WeatherDto {
        return api.getWeatherByCity(
            city = city,
            apiKey = apiKey
        )
    }
}