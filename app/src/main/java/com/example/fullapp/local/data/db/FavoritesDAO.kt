package com.example.fullapp.local.data.db

import androidx.room.*
import com.example.fullapp.local.data.db.Constants.DELETE_FAVORITE
import com.example.fullapp.local.data.db.Constants.GET_FAVORITES
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.model.MovieDTO
import kotlinx.coroutines.flow.Flow

object Constants{
    const val DELETE_FAVORITE = "DELETE FROM favorites_table WHERE id = :id"
    const val GET_FAVORITES = "SELECT * FROM favorites_table"
}

@Dao
interface FavoritesDAO {

    @Query(GET_FAVORITES)
    fun getFavorites() : Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(
        favorite: Movie
    )

    @Query(DELETE_FAVORITE)
    suspend fun deleteFavorite(id: Int)
}

