package ua.knu.maksym_pashchenko.weatherapp.presentation.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather

@Composable
fun WeatherResult(
    weather: Weather
) {
    Column {
        Text(
            text = weather.cityName,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${weather.temperature}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = weather.description
        )
    }
}