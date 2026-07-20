package ua.knu.maksym_pashchenko.weatherapp.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import ua.knu.maksym_pashchenko.weatherapp.presentation.details.viewmodel.WeatherDetailsViewModel

@Composable
fun WeatherDetailsScreen(
    city: String,
    viewModel: WeatherDetailsViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(city) {
        viewModel.loadWeather(city)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val state = uiState) {
            WeatherDetailsUiState.Loading -> {
                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Loading weather details...")
            }

            is WeatherDetailsUiState.Success -> {
                WeatherDetailsContent(
                    weather = state.weather
                )
            }

            is WeatherDetailsUiState.Error -> {
                Text(
                    text = "Error",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back")
        }
    }
}


@Composable
private fun WeatherDetailsContent(
    weather: Weather
) {
    Text(
        text = weather.cityName,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(24.dp))

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding()
        ) {
            WeatherInfoRow(
                label = "Temperature",
                value = "${weather.temperature}°C"
            )

            WeatherInfoRow(
                label = "Description",
                value = weather.description
            )

            WeatherInfoRow(
                label = "Humidity",
                value = "${weather.humidity}%"
            )

            WeatherInfoRow(
                label = "Wind speed",
                value = "${weather.windSpeed} m/s"
            )

            WeatherInfoRow(
                label = "Updated at",
                value = weather.updatedAt
            )
        }
    }
}

@Composable
private fun WeatherInfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}