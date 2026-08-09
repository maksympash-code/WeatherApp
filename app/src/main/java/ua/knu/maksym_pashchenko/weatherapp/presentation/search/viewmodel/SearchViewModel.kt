package ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.SearchUiState
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val weatherRepository: WeatherRepository,
    private val favoriteCityRepository: FavoriteCityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _favoriteCities = MutableStateFlow<List<String>>(emptyList())

    val favoriteCities: StateFlow<List<String>> = _favoriteCities.asStateFlow()

    fun searchWeather(city: String) {
        if (city.isBlank()) {
            _uiState.value = SearchUiState.Error("City name cannot be empty")
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            try {
                val weather = weatherRepository.getWeatherByCity(city.trim())
                _uiState.value = SearchUiState.Success(weather)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error("City not found")
            }
        }
    }

    private fun observeFavoriteCities() {
        viewModelScope.launch {
            favoriteCityRepository
                .getFavoriteCities()
                .collect { cities ->
                    _favoriteCities.value = cities
                }
        }
    }

    init {
        observeFavoriteCities()
    }
}