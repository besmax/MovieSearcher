package bes.max.moviesearcher.network

import bes.max.moviesearcher.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://imdb-api.com"
private const val API_KEY = "k_hmej0hq1"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApiService {

    @GET("/en/API/SearchMovie/${API_KEY}/{query}")
    fun getMovies(@Path("query") query: String): Call<MovieResponse>
}

object MovieApi {
    val movieRetrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}

