package ua.knu.maksym_pashchenko.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ua.knu.maksym_pashchenko.weatherapp.data.local.entity.FavoriteCityEntity

@Dao
interface FavoriteCityDao {

    @Query("SELECT * FROM favorite_cities ORDER BY addedAt DESC")
    fun getFavoriteCities() : Flow<List<FavoriteCityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteCity(city: FavoriteCityEntity)

    @Query("DELETE FROM favorite_cities WHERE cityName = :cityName")
    suspend fun removeFavoriteCity(cityName: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cities WHERE cityName = :cityName)")
    fun isFavorite(cityName: String) : Flow<Boolean>
}