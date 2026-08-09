package ua.knu.maksym_pashchenko.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteCityRepository {
    fun getFavoriteCities(): Flow<List<String>>

    fun isFavorite(cityName: String): Flow<Boolean>

    suspend fun addFavoriteCity(cityName: String)
    suspend fun removeFavoriteCity(cityName: String)
}