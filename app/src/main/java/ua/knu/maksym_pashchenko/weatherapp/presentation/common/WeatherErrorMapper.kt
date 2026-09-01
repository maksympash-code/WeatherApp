package ua.knu.maksym_pashchenko.weatherapp.presentation.common

import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toWeatherErrorMessage(): String {
    return when (this) {

        is UnknownHostException -> {
            "No Internet connection"
        }

        is SocketTimeoutException -> {
            "Request timed out"
        }

        is retrofit2.HttpException -> {
            when (code()) {
                404 -> "City not found"
                401 -> "Invalid API key"
                429 -> "Too many requests"

                in 500..599 -> {
                    "Weather service is temporarily unavailable"
                }

                else ->  {
                    "Weather API error"
                }
            }
        }

        else -> {
            "Something went wrong"
        }
    }
}