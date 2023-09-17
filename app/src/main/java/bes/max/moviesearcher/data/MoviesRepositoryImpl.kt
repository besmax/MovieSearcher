package bes.max.moviesearcher.data

import bes.max.moviesearcher.data.dto.responses.MovieCastResponse
import bes.max.moviesearcher.data.dto.responses.MovieDetailsResponse
import bes.max.moviesearcher.data.dto.requests.MovieDetailsSearchRequest
import bes.max.moviesearcher.data.dto.requests.MovieFullCastRequest
import bes.max.moviesearcher.data.dto.requests.MovieSearchRequest
import bes.max.moviesearcher.data.dto.responses.MovieSearchResponse
import bes.max.moviesearcher.data.mappers.MovieCastResponseToMovieFullCastMapper
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieCastPerson
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.domain.models.MovieFullCast
import bes.max.moviesearcher.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: MovieCastResponseToMovieFullCastMapper
) : MoviesRepository {

    override fun getMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MovieSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieSearchResponse) {
                    Resource.Success(results.map {
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description
                        )
                    })
                }
            }

            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsSearchRequest(movieId))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id = this.id,
                            title = this.title,
                            imDbRating = this.imDbRating ?: "",
                            year = this.year,
                            countries = this.countries,
                            genres = this.genres,
                            directors = this.directors,
                            writers = this.writers,
                            stars = this.stars,
                            plot = this.plot
                        )
                    )
                }
            }

            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun getMovieFullCast(movieId: String): Resource<MovieFullCast> {
        val response = networkClient.doRequest(MovieFullCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> Resource.Success(mapper.convert(response as MovieCastResponse))
            else -> Resource.Error("Ошибка сервера")
        }
    }


}