package com.example.fullapp.local.data.repository

import android.util.Log
import com.example.fullapp.local.data.db.FavoritesDAO
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val dao: FavoritesDAO) : FavoritesRepository {

    override fun getFavorites(): Flow<List<Movie>> {

        return dao.getFavorites()
    }

    override suspend fun addFavorite(movie: Movie) {
        dao.insertFavorite(movie)
    }

    override suspend fun removeFavorite(id: Int) {
        dao.deleteFavorite(id)
    }
}