package ua.knu.maksym_pashchenko.weatherapp.presentation.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import ua.knu.maksym_pashchenko.weatherapp.presentation.common.WeatherIcon

@Composable
fun WeatherResult(
    weather: Weather
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherIcon(
                iconUrl = weather.iconUrl,
                description = weather.description,
                modifier = Modifier.size(72.dp)
            )

            Column {
                Text(
                    text = weather.cityName,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "${weather.temperature}°C",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(text = weather.description)
            }
        }
    }

}