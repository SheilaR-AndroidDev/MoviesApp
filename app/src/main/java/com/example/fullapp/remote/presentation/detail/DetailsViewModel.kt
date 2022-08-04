package com.example.fullapp.remote.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    fun saveFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO){
            favoritesRepository.addFavorite(movie)
        }
    }

    fun deleteFavorite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.removeFavorite(id)
        }
    }

    fun getFavorites() {
        var list : List<Movie> = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.getFavorites().collect(){
                Log.i("tag", "favorite= $it")
            }
        }

        list.forEach {
            Log.i("tag", "favorite= ${it.title}")
        }
    }
}