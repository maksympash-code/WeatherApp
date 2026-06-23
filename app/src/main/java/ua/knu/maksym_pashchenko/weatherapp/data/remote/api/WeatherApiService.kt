package ua.knu.maksym_pashchenko.weatherapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ua.knu.maksym_pashchenko.weatherapp.data.remote.dto.WeatherDto

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherDto
}