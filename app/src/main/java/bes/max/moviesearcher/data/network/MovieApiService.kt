package bes.max.moviesearcher.data.network

import bes.max.moviesearcher.data.dto.responses.MovieDetailsResponse
import bes.max.moviesearcher.data.dto.responses.MovieCastResponse
import bes.max.moviesearcher.data.dto.responses.MovieSearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "k_zcuw1ytf"

interface MovieApiService {

    @GET("/en/API/SearchMovie/$API_KEY/{query}")
    suspend fun getMovies(@Path("query") query: String): MovieSearchResponse

    @GET("/en/API/Title/$API_KEY/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("/en/API/FullCast/$API_KEY/{movie_id}")
    suspend fun getMovieCast(@Path("movie_id") movieId: String): MovieCastResponse

}


