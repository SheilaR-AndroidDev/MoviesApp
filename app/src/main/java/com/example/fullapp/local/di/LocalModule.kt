package com.example.fullapp.local.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fullapp.local.data.db.FavoritesDAO
import com.example.fullapp.local.data.db.FavoritesDatabase
import com.example.fullapp.local.data.repository.FavoritesRepositoryImpl
import com.example.fullapp.local.domain.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule: Application() {

    @Provides
    @Singleton
    fun providesRoomDatabase(app: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            FavoritesDatabase::class.java,
            FavoritesDatabase.database_name
        ).build()
    }

    @Provides
    @Singleton
    fun providesDAO(favoritesDatabase: FavoritesDatabase): FavoritesDAO =
        favoritesDatabase.favoritesDao()

    @Provides
    @Singleton
    fun providesFavoritesRepository(db: FavoritesDatabase): FavoritesRepository =
        FavoritesRepositoryImpl(db.favoritesDao())

}