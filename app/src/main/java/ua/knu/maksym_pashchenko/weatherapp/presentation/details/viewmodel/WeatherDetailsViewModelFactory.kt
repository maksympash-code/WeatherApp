package ua.knu.maksym_pashchenko.weatherapp.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class WeatherDetailsViewModelFactory(
    private val weatherRepository: WeatherRepository,
    private val favoriteCityRepository: FavoriteCityRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherDetailsViewModel(weatherRepository, favoriteCityRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}