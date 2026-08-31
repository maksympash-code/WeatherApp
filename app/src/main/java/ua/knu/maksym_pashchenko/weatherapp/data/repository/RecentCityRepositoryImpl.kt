package ua.knu.maksym_pashchenko.weatherapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.knu.maksym_pashchenko.weatherapp.data.local.dao.RecentCityDao
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.RecentCityEntity
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.RecentCityRepository

class RecentCityRepositoryImpl(
    private val dao: RecentCityDao
): RecentCityRepository {
    override fun getAllRecentCities(): Flow<List<String>> {
        return dao.getAllRecentCities().
                map { cities ->
                    cities.map { it.cityName }
                }
    }

    override suspend fun addRecentCity(cityName: String) {
        val entity = RecentCityEntity(
            cityName = cityName,
            searchAt = System.currentTimeMillis()
        )

        dao.addRecentCity(entity)
    }

}