package ua.knu.maksym_pashchenko.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface RecentCityRepository {

    fun getAllRecentCities(): Flow<List<String>>

    suspend fun addRecentCity(cityName: String)
}