package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testesky.model.MovieResult
import com.example.testesky.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    private val movieList: MutableLiveData<List<MovieResult>> = MutableLiveData()
    val movieResult: LiveData<List<MovieResult>> = movieList
    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    val loadingResult: LiveData<Boolean> = loading
    private val error: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = error
    private val repository = MovieRepository()

    fun getMovies() {
        disposable.add(
            repository.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .doAfterTerminate { loading.value = false }
                .subscribe({ movies -> movieList.value = movies }, { t ->
                    error.value = t.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}