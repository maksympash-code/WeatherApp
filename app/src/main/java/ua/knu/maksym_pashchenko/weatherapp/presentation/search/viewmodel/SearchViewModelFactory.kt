package ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository

class SearchViewModelFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}