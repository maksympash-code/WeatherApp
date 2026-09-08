package ua.knu.maksym_pashchenko.weatherapp.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.component.CityCard
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.component.WeatherResult
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
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


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Weather App")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {

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
            }

            item {
                Text(
                    text = "Favorite cities",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (favoriteCities.isEmpty()) {
                item {
                    Text(
                        text = "No favourite cities yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            } else {
                items(favoriteCities) { cityName ->
                    CityCard(
                        cityName = cityName,
                        onClick = {
                            onDetailsClick(cityName)
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Recent Cities",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (recentCities.isEmpty()) {

                item {
                    Text(
                        text = "No recent cities yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {

                items(recentCities) {recentCityName ->
                    CityCard(
                        cityName = recentCityName,
                        onClick = {
                            onDetailsClick(recentCityName)
                        }
                    )
                }
            }

            item {
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
    }
}