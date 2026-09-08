package ua.knu.maksym_pashchenko.weatherapp.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.knu.maksym_pashchenko.weatherapp.domain.model.Weather
import ua.knu.maksym_pashchenko.weatherapp.presentation.common.WeatherIcon
import ua.knu.maksym_pashchenko.weatherapp.presentation.details.viewmodel.WeatherDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    city: String,
    viewModel: WeatherDetailsViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(city) {
        viewModel.loadWeather(city)
        viewModel.observeFavorite(city)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Weather details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Text("←")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
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
                        weather = state.weather,
                        isFavorite = isFavorite,
                        onFavoriteClick = {
                            viewModel.toggleFavorite(city)
                        }
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.loadWeather(city)
                        }
                    ) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }

}


@Composable
private fun WeatherDetailsContent(
    weather: Weather,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    WeatherIcon(
        iconUrl = weather.iconUrl,
        description = weather.description,
        modifier = Modifier.size(96.dp)
    )

    Spacer(Modifier.height(8.dp))

    Text(
        text = weather.cityName,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = "${weather.temperature}°C",
        style = MaterialTheme.typography.displaySmall
    )

    Text(
        text = weather.description,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(24.dp))

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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

    Button(
        onClick = onFavoriteClick
    ) {
        Text(
            text = if (isFavorite) {
                "Remove from favorites"
            } else {
                "Add to favorites"
            }
        )
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