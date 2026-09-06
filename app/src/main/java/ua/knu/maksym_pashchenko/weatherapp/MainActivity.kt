package ua.knu.maksym_pashchenko.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ua.knu.maksym_pashchenko.weatherapp.presentation.navigation.AppNavGraph
import ua.knu.maksym_pashchenko.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = (application as WeatherApplication).container

        enableEdgeToEdge()

        setContent {
            WeatherAppTheme {
                AppNavGraph(
                    weatherRepository = container.weatherRepository,
                    favoriteCityRepository = container.favoriteCityRepository,
                    recentCityRepository = container.recentCityRepository
                )
            }
        }
    }
}

