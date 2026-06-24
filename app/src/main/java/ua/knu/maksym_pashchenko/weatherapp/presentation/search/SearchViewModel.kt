package ua.knu.maksym_pashchenko.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val repository: WeatherRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchWeather(city: String) {
        if (city.isBlank()) {
            _uiState.value = SearchUiState.Error("City name cannot be empty")
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            try {
                val weather = repository.getWeatherByCity(city)
                _uiState.value = SearchUiState.Success(weather)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error("City not found")
            }
        }
    }
}