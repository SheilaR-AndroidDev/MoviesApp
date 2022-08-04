package com.example.fullapp.local.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.model.MovieDTO

@Database(entities = [Movie::class], version = 1)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDAO

    companion object{
        const val database_name = "favorites_db"
    }
}