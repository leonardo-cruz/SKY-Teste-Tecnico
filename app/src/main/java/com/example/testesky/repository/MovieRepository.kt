package com.example.testesky.repository

import com.example.testesky.model.MovieResult
import com.example.testesky.network.RetrofitService
import io.reactivex.Observable

class MovieRepository {

    fun getMovies() : Observable<List<MovieResult>>{
        return RetrofitService.service.allMovies()
    }
}