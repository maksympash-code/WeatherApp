package ua.knu.maksym_pashchenko.weatherapp.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String?,
    val humidity: Int,
    val windSpeed: Double,
    val updatedAt: String
)
