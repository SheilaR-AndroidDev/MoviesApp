package com.example.fullapp.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fullapp.local.data.db.FavoritesDAO
import com.example.fullapp.local.data.db.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): FavoritesDatabase {
        return Room.databaseBuilder(
            context,
            FavoritesDatabase::class.java,
            FavoritesDatabase.database_name
        ).build()
    }

    @Provides
    @Singleton
    fun providesDAO(favoritesDatabase: FavoritesDatabase): FavoritesDAO =
        favoritesDatabase.favoritesDao()

}