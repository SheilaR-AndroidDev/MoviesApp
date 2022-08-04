package com.example.fullapp.remote.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fullapp.remote.Constants
import com.example.fullapp.remote.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import com.example.fullapp.remote.domain.model.Result

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
)
: ViewModel() {
    // TODO: Implement the ViewModel
    private var _allMovies = MutableLiveData<List<Result>>()
    val allMovies : LiveData<List<Result>> = _allMovies

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val disposable = CompositeDisposable()

    init {
        retrieveAllMovies()
    }

    private fun retrieveAllMovies() {
        disposable.add(
            repository.getAllMovies(Constants.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _allMovies.value = it.results
                }, {
                    _error.value = it.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}