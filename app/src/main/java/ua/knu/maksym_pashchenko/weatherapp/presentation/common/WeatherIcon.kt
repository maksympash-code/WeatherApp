package ua.knu.maksym_pashchenko.weatherapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun WeatherIcon(
    iconUrl: String?,
    description: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = iconUrl,
        contentDescription = description,
        modifier = modifier
    )
}