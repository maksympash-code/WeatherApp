package ua.knu.maksym_pashchenko.weatherapp.data.mapper

import ua.knu.maksym_pashchenko.weatherapp.data.remote.dto.WeatherDto
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun WeatherDto.toDomain(): Weather {
    val weatherDescription = weather.firstOrNull()

    val updatedAtText = SimpleDateFormat(
        "dd MMM yyyy HH:mm",
        Locale.getDefault()
    ).format(Date(dt * 1000))

    return Weather(
        cityName = name,
        temperature = main.temp,
        description = weatherDescription?.description ?: "No description",
        iconUrl = weatherDescription?.icon?.let { iconCode ->
            "https://openweathermap.org/img/wn/${iconCode}@2x.png"
        },
        humidity = main.humidity,
        windSpeed = wind.speed,
        updatedAt = updatedAtText
    )
}