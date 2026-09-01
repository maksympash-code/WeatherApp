package ua.knu.maksym_pashchenko.weatherapp.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.component.WeatherResult
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onDetailsClick: (String) -> Unit
) {
    var city by rememberSaveable { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favoriteCities by viewModel.favoriteCities.collectAsStateWithLifecycle()
    val recentCities by viewModel.recentCities.collectAsStateWithLifecycle()

    val isLoading = uiState is SearchUiState.Loading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Weather App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = {
                Text(text = "Enter the city")
            },
            singleLine = true,
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val trimmedCity = city.trim()

                viewModel.searchWeather(trimmedCity)

                if (trimmedCity.isNotBlank()) {
                    onDetailsClick(trimmedCity)
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (isLoading) {
                    "Loading..."
                } else {
                    "Search"
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (favoriteCities.isNotEmpty()) {
            Text(
                text = "Favorite cities",
                style = MaterialTheme.typography.titleMedium
            )

            favoriteCities.forEach { cityName ->
                Button(
                    onClick = { onDetailsClick(cityName) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cityName)
                }
            }
        }

        if (recentCities.isNotEmpty()) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Cities",
                style = MaterialTheme.typography.titleMedium
            )

            recentCities.forEach { cityName ->
                Button(
                    onClick = {
                        onDetailsClick(cityName)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cityName)
                }
            }
        }

        when (val state = uiState) {
            SearchUiState.Idle -> {
                Text("Enter city name to search weather")
            }

            SearchUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is SearchUiState.Success -> {
                WeatherResult(weather = state.weather)
            }

            is SearchUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}