package com.example.fullapp.remote.di

import com.example.fullapp.remote.Constants.BASE_URL
import com.example.fullapp.remote.data.api.MoviesApi
import com.example.fullapp.remote.data.repository.MoviesRepositoryImpl
import com.example.fullapp.remote.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesMoviesApi(): MoviesApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(api: MoviesApi) : MoviesRepository{
        return MoviesRepositoryImpl(api)
    }

}