package ua.knu.maksym_pashchenko.weatherapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.knu.maksym_pashchenko.weatherapp.data.local.dao.FavoriteCityDao
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.FavoriteCityEntity
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.FavoriteCityRepository

class FavoriteCityRepositoryImpl(
    private val dao: FavoriteCityDao
) : FavoriteCityRepository {
    override fun getFavoriteCities(): Flow<List<String>> {
        return dao.getFavoriteCities()
            .map { cities ->
                cities.map { it.cityName }
            }
    }

    override fun isFavorite(cityName: String): Flow<Boolean> {
        return dao.isFavorite(cityName)
    }

    override suspend fun addFavoriteCity(cityName: String) {
        val entity = FavoriteCityEntity(
            cityName = cityName,
            addedAt = System.currentTimeMillis()
        )

        dao.addFavoriteCity(entity)
    }

    override suspend fun removeFavoriteCity(cityName: String) {
        dao.removeFavoriteCity(cityName)
    }
}