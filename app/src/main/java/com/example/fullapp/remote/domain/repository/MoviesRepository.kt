package com.example.fullapp.remote.domain.repository

import com.example.fullapp.remote.domain.model.PopularMovies
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {

    fun getAllMovies(api_key: String): Single<PopularMovies>

    fun getMovie(api_key: String, id: Int) : Single<PopularMovies>
}