package bes.max.moviesearcher.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.dto.requests.MovieDetailsSearchRequest
import bes.max.moviesearcher.data.dto.requests.MovieFullCastRequest
import bes.max.moviesearcher.data.dto.requests.MovieSearchRequest
import bes.max.moviesearcher.data.dto.responses.Response
import java.lang.IllegalArgumentException

class RetrofitNetworkClient(
    private val imdbService: MovieApiService,
    private val context: Context,
) : NetworkClient {

    override fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MovieSearchRequest) && (dto !is MovieDetailsSearchRequest) && (dto !is MovieFullCastRequest)) {
            return Response().apply { resultCode = 400 }
        }

        val response = when (dto) {
            is MovieSearchRequest -> imdbService.getMovies(dto.expression).execute()
            is MovieDetailsSearchRequest -> imdbService.getMovieDetails(dto.movieId).execute()
            is MovieFullCastRequest -> imdbService.getMovieCast(dto.movieId).execute()
            else -> throw IllegalArgumentException("Unknown dto: ${dto.toString()}")
        }
        val body = response.body()
        return body?.apply { resultCode = response.code() } ?: Response().apply {
            resultCode = response.code()
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}