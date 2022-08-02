package com.example.fullapp.local.data.db

import androidx.room.*
import com.example.fullapp.local.data.db.Constants.DELETE_MOVIE
import com.example.fullapp.local.domain.model.MovieDTO

object Constants{
    const val DELETE_MOVIE = "DELETE FROM favorites_table WHERE id = :id"
}

@Dao
interface FavoritesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(
        favorite: MovieDTO
    )

    @Query(DELETE_MOVIE)
    suspend fun deleteFavorite(id: Int)
}

