package com.example.fullapp.remote.data.api

import com.example.fullapp.local.domain.model.MovieDTO
import com.example.fullapp.remote.Constants.API_KEY_STRING
import com.example.fullapp.remote.domain.model.PopularMovies
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    fun getAllMovies(
        @Query(API_KEY_STRING) api_key: String
    ) : Single<PopularMovies>

    @GET("3/movie/{movie_id}")
    fun getMovie(
        @Query(API_KEY_STRING) api_key: String,
        @Path("movie_id") movie_id: Int) : Single<PopularMovies>

}