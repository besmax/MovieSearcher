package bes.max.moviesearcher.data.network

import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.dto.MovieSearchRequest
import bes.max.moviesearcher.data.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient : NetworkClient {

    private val imdbBaseUrl = "https://imdb-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(imdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val imdbService = retrofit.create(MovieApiService::class.java)

    override fun doRequest(dto: Any): Response {
        if (dto is MovieSearchRequest) {
            val resp = imdbService.getMovies(dto.expression).execute()

            val body = resp.body() ?: Response()

            return body.apply { resultCode = resp.code() }
        } else {
            return Response().apply { resultCode = 400 }
        }
    }
}