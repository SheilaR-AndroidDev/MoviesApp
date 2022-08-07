package com.example.fullapp.local.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.fullapp.local.domain.model.Movie
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class FavoritesDaoTest {

    private lateinit var dao : FavoritesDAO
    lateinit var db: FavoritesDatabase

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

   @Before
   fun setup(){
       db = Room.inMemoryDatabaseBuilder(
           ApplicationProvider.getApplicationContext(),
           FavoritesDatabase::class.java
       ).allowMainThreadQueries()
           .build()

       dao = db.favoritesDao()
   }

    @After
    fun teardown(){
        db.close()
    }

    @Test
    fun favoriteIsAdded() = runBlocking{
        val movie = Movie(1, "overview", "poster", "release", "title")
        dao.insertFavorite(movie)
        val favs = dao.getFavorites().toList()
        assertThat(movie).isIn(favs)
    }
}