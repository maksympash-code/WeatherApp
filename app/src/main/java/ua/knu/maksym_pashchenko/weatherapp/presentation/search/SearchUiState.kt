package ua.knu.maksym_pashchenko.weatherapp.presentation.search

import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather

sealed interface SearchUiState {

    data object Idle : SearchUiState

    data object Loading : SearchUiState

    data class Success(
        val weather: Weather
    ) : SearchUiState

    data class Error(
        val message: String
    ) : SearchUiState
}
