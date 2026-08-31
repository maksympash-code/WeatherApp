package ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.RecentCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class SearchViewModelFactory(
    private val weatherRepository: WeatherRepository,
    private val favoriteCityRepository: FavoriteCityRepository,
    private val recentCityRepository: RecentCityRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(
                weatherRepository,
                favoriteCityRepository,
                recentCityRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}