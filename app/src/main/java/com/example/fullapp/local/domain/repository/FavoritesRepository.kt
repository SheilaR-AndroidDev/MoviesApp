package com.example.fullapp.local.domain.repository

import com.example.fullapp.local.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getFavorites() : Flow<List<Movie>>

    suspend fun addFavorite(movie: Movie)

    suspend fun removeFavorite(id: Int)
}