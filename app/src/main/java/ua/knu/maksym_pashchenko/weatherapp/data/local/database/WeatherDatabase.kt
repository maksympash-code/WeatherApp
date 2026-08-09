package ua.knu.maksym_pashchenko.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.knu.maksym_pashchenko.weatherapp.data.local.dao.FavoriteCityDao
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.FavoriteCityEntity

@Database(
    entities = [
        FavoriteCityEntity::class
               ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun favoriteCityDao() : FavoriteCityDao
}