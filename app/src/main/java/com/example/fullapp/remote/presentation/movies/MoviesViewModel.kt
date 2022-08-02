package com.example.fullapp.remote.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fullapp.remote.Constants.API_KEY
import com.example.fullapp.remote.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import com.example.fullapp.remote.domain.model.Result

@HiltViewModel
class MoviesViewModel @Inject constructor(
    repository: MoviesRepository
)
: ViewModel() {
    // TODO: Implement the ViewModel
    private var _allMovies = MutableLiveData<List<Result>>()
    val allMovies : LiveData<List<Result>> = _allMovies

    private val disposable = CompositeDisposable()

    init {

       disposable.add(
            repository.getAllMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleSucess(it.results)
                },{
                    handleError(it)
           })
        )
    }

    private fun handleSucess(list: List<Result>){
        _allMovies.value = list
    }

    private fun handleError(error: Throwable){
        Log.i("tag", "error al consultar la api ${error.message}")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}