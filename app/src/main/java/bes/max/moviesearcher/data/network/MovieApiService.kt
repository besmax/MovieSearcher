package bes.max.moviesearcher.data.network

import bes.max.moviesearcher.data.dto.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "k_zcuw1ytf"

interface MovieApiService {

    @GET("/en/API/SearchMovie/$API_KEY/{query}")
    fun getMovies(@Path("query") query: String): Call<MovieSearchResponse>
}

