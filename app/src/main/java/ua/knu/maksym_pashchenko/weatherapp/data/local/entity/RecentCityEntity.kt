package ua.knu.maksym_pashchenko.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_cities")
data class RecentCityEntity(
    @PrimaryKey
    val cityName: String,
    val searchAt: Long
)
