package bes.max.moviesearcher.data.network

import bes.max.moviesearcher.data.dto.MovieDetailsResponse
import bes.max.moviesearcher.data.dto.MovieFullCastResponse
import bes.max.moviesearcher.data.dto.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "k_zcuw1ytf"

interface MovieApiService {

    @GET("/en/API/SearchMovie/$API_KEY/{query}")
    fun getMovies(@Path("query") query: String): Call<MovieSearchResponse>

    @GET("/en/API/Title/$API_KEY/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/en/API/FullCast/$API_KEY/{movie_id}")
    fun getMovieCast(@Path("movie_id") movieId: String): Call<MovieFullCastResponse>

}


