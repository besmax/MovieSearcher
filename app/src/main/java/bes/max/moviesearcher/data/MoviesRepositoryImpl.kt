package bes.max.moviesearcher.data

import bes.max.moviesearcher.data.dto.MovieSearchRequest
import bes.max.moviesearcher.data.dto.MovieSearchResponse
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun getMovies(expression: String): List<Movie> {
        val response = networkClient.doRequest(MovieSearchRequest(expression))
        if (response.resultCode == 200) {
            return (response as MovieSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description)
            }
        } else {
            return emptyList()
        }
    }
}