package ua.knu.maksym_pashchenko.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.RecentCityEntity

@Dao
interface RecentCityDao {
    @Query("SELECT * FROM recent_cities ORDER by searchAt DESC")
    fun getAllRecentCities(): Flow<List<RecentCityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentCity(cityName: String)
}