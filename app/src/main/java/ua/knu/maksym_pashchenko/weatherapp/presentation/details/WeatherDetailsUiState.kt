package ua.knu.maksym_pashchenko.weatherapp.presentation.details

import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather

sealed interface WeatherDetailsUiState {
    data object Loading: WeatherDetailsUiState

    data class Success(
        val weather: Weather
    ) : WeatherDetailsUiState

    data class Error(
        val message: String
    ) : WeatherDetailsUiState
}