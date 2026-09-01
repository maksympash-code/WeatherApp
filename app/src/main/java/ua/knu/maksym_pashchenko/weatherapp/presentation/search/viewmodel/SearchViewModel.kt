package ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.RecentCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository
import ua.knu.maksym_pashchenko.weatherapp.presentation.common.toWeatherErrorMessage
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.SearchUiState
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val weatherRepository: WeatherRepository,
    private val favoriteCityRepository: FavoriteCityRepository,
    private val recentCityRepository: RecentCityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _favoriteCities = MutableStateFlow<List<String>>(emptyList())

    val favoriteCities: StateFlow<List<String>> = _favoriteCities.asStateFlow()

    private val _recentCities = MutableStateFlow<List<String>>(emptyList())
    val recentCities: StateFlow<List<String>> = _recentCities.asStateFlow()

    fun searchWeather(city: String) {
        if (city.isBlank()) {
            _uiState.value = SearchUiState.Error("Enter city name")
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            try {
                val trimmedCity = city.trim()
                val weather = weatherRepository.getWeatherByCity(trimmedCity)

                recentCityRepository.addRecentCity(trimmedCity)

                _uiState.value = SearchUiState.Success(weather)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error(e.toWeatherErrorMessage())
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

    private fun observeRecentCities() {
        viewModelScope.launch {
            recentCityRepository
                .getAllRecentCities()
                .collect { cities ->
                    _recentCities.value = cities
                }
        }
    }

    init {
        observeFavoriteCities()
        observeRecentCities()
    }
}