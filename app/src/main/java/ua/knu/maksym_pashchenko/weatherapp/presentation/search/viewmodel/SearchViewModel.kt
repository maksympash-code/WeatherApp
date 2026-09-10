package ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.RecentCityRepository

class SearchViewModel(
    private val favoriteCityRepository: FavoriteCityRepository,
    private val recentCityRepository: RecentCityRepository
) : ViewModel() {

    private val _favoriteCities = MutableStateFlow<List<String>>(emptyList())

    val favoriteCities: StateFlow<List<String>> = _favoriteCities.asStateFlow()

    private val _recentCities = MutableStateFlow<List<String>>(emptyList())
    val recentCities: StateFlow<List<String>> = _recentCities.asStateFlow()

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