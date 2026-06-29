package ua.knu.maksym_pashchenko.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.knu.maksym_pashchenko.weatherapp.data.remote.RetrofitInstance
import ua.knu.maksym_pashchenko.weatherapp.data.repository.WeatherRepositoryImpl
import ua.knu.maksym_pashchenko.weatherapp.presentation.navigation.AppNavGraph
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.SearchScreen
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModel
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModelFactory
import ua.knu.maksym_pashchenko.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

    private val weatherRepository by lazy {
        WeatherRepositoryImpl(
            apiService = RetrofitInstance.weatherApiService,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                AppNavGraph(
                    weatherRepository = weatherRepository
                )
            }
        }
    }
}

