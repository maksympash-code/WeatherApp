package ua.knu.maksym_pashchenko.weatherapp.domain.repository

import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): Weather
}