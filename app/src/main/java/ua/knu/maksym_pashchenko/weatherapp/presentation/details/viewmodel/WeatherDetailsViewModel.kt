package ua.knu.maksym_pashchenko.weatherapp.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository
import ua.knu.maksym_pashchenko.weatherapp.presentation.details.WeatherDetailsUiState

class WeatherDetailsViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<WeatherDetailsUiState>(WeatherDetailsUiState.Loading)

    val uiState : StateFlow<WeatherDetailsUiState> = _uiState.asStateFlow()

    fun loadWeather(city: String) {
        val trimmedCity = city.trim()

        if (trimmedCity.isBlank()) {
            _uiState.value = WeatherDetailsUiState.Error("City name is missing")
            return
        }

        viewModelScope.launch {
            _uiState.value = WeatherDetailsUiState.Loading

            try {
                val weather = repository.getWeatherByCity(trimmedCity)
                _uiState.value = WeatherDetailsUiState.Success(weather)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = WeatherDetailsUiState.Error("Failed to load weather details")
            }
        }
    }
}