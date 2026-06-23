package ua.knu.maksym_pashchenko.weatherapp.data.remote.dto

data class WeatherDto(
    val name: String,
    val main: MainDto,
    val weather: List<WeatherDescriptionDto>,
    val wind: WindDto,
    val dt: Long
)


data class MainDto(
    val temp: Double,
    val humidity: Int,
)

data class WeatherDescriptionDto(
    val description: String,
    val icon: String
)

data class WindDto(
    val speed: Double
)