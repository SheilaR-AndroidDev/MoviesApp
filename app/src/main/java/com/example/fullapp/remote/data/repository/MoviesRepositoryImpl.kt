package com.example.fullapp.remote.data.repository

import com.example.fullapp.local.domain.model.MovieDTO
import com.example.fullapp.remote.data.api.MoviesApi
import com.example.fullapp.remote.domain.model.PopularMovies
import com.example.fullapp.remote.domain.repository.MoviesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MoviesRepositoryImpl(private val api: MoviesApi): MoviesRepository {

    override fun getAllMovies(api_key: String) : Single<PopularMovies> {
        return api.getAllMovies(api_key)
    }

    override fun getMovie(api_key: String, id: Int): Single<PopularMovies> {
        return api.getMovie(api_key = api_key, movie_id = id)
    }
}