package ua.knu.maksym_pashchenko.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.SearchScreen
import ua.knu.maksym_pashchenko.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                SearchScreen(
                    onWeatherClick = { city ->
                        println("Selected city: $city")
                    }
                )
            }
        }
    }
}

