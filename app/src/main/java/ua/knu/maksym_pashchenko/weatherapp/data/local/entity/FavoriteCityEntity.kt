package ua.knu.maksym_pashchenko.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_cities")
data class FavoriteCityEntity(
    @PrimaryKey
    val cityName: String,
    val addedAt: Long
)