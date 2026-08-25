package ua.knu.maksym_pashchenko.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.knu.maksym_pashchenko.weatherapp.data.local.dao.FavoriteCityDao
import ua.knu.maksym_pashchenko.weatherapp.data.local.dao.RecentCityDao
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.FavoriteCityEntity
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.RecentCityEntity

@Database(
    entities = [
        FavoriteCityEntity::class,
        RecentCityEntity::class
    ],
    version = 2
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun favoriteCityDao(): FavoriteCityDao
    abstract fun recentCitiesDao(): RecentCityDao
}