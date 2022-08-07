package com.example.fullapp.remote.presentation.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private var _favorites = MutableLiveData<List<Movie>>()
    private val favorites : LiveData<List<Movie>> = _favorites

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
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.getFavorites().collect{
               _favorites.value = it
            }
        }
    }
}