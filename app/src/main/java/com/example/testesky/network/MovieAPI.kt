package com.example.testesky.network

import com.example.testesky.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieAPI {
    @GET("movies")
    fun allMovies() : Observable<List<MovieResult>>
}