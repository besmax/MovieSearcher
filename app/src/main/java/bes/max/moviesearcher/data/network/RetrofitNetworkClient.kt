package bes.max.moviesearcher.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.dto.requests.MovieDetailsSearchRequest
import bes.max.moviesearcher.data.dto.requests.MovieFullCastRequest
import bes.max.moviesearcher.data.dto.requests.MovieSearchRequest
import bes.max.moviesearcher.data.dto.requests.NamesSearchRequest
import bes.max.moviesearcher.data.dto.responses.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

private const val TAG = "RetrofitNetworkClient"

class RetrofitNetworkClient(
    private val imdbService: MovieApiService,
    private val context: Context,
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MovieSearchRequest) && (dto !is MovieDetailsSearchRequest) && (dto !is MovieFullCastRequest) && (dto !is NamesSearchRequest)) {
            return Response().apply { resultCode = 400 }
        }

        return withContext(Dispatchers.IO) {
            when (dto) {
                is MovieSearchRequest -> {
                    try {
                        imdbService.getMovies(dto.expression).apply { resultCode = 200 }
                    } catch (e: Throwable) {
                        Log.e(TAG, "Error during MovieSearchRequest: ${e.toString()}")
                        Response().apply { resultCode = 500 }
                    }
                }

                is MovieDetailsSearchRequest -> {
                    try {
                        imdbService.getMovieDetails(dto.movieId).apply { resultCode = 200 }
                    } catch (e: Throwable) {
                        Log.e(TAG, "Error during MovieDetailsSearchRequest: ${e.toString()}")
                        Response().apply { resultCode = 500 }
                    }
                }

                is MovieFullCastRequest -> {
                    try {
                        imdbService.getMovieCast(dto.movieId).apply { resultCode = 200 }
                    } catch (e: Throwable) {
                        Log.e(TAG, "Error during MovieFullCastRequest: ${e.toString()}")
                        Response().apply { resultCode = 500 }
                    }
                }

                is NamesSearchRequest -> {
                    try {
                        imdbService.searchNames(dto.expression).apply { resultCode = 200 }

                    } catch (e: Throwable) {
                        Log.e(TAG, "Error during NamesSearchRequest: ${e.toString()}")
                        Response().apply { resultCode = 500 }
                    }
                }

                else -> throw IllegalArgumentException("Unknown dto: ${dto.toString()}")
            }
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