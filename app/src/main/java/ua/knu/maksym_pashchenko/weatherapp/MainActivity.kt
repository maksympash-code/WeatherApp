package ua.knu.maksym_pashchenko.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import ua.knu.maksym_pashchenko.weatherapp.data.local.database.WeatherDatabase
import ua.knu.maksym_pashchenko.weatherapp.data.remote.RetrofitInstance
import ua.knu.maksym_pashchenko.weatherapp.data.repository.FavoriteCityRepositoryImpl
import ua.knu.maksym_pashchenko.weatherapp.data.repository.RecentCityRepositoryImpl
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

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java,
            "weather_database",
        ).build()
    }

    private val favoriteCityRepository by lazy {
        FavoriteCityRepositoryImpl(
            dao = database.favoriteCityDao()
        )
    }

    private val recentCityRepository by lazy {
        RecentCityRepositoryImpl(
            dao = database.recentCitiesDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                AppNavGraph(
                    weatherRepository = weatherRepository,
                    favoriteCityRepository = favoriteCityRepository,
                    recentCityRepository = recentCityRepository
                )
            }
        }
    }
}

