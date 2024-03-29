package bes.max.moviesearcher.data.repositories

import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.db.MovieDao
import bes.max.moviesearcher.data.dto.requests.MovieDetailsSearchRequest
import bes.max.moviesearcher.data.dto.requests.MovieFullCastRequest
import bes.max.moviesearcher.data.dto.requests.MovieSearchRequest
import bes.max.moviesearcher.data.dto.responses.MovieCastResponse
import bes.max.moviesearcher.data.dto.responses.MovieDetailsResponse
import bes.max.moviesearcher.data.dto.responses.MovieSearchResponse
import bes.max.moviesearcher.data.mappers.MovieCastResponseToMovieFullCastMapper
import bes.max.moviesearcher.data.mappers.MovieDbMapper
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val dao: MovieDao,
    private val mapper: MovieCastResponseToMovieFullCastMapper,
    private val movieDbMapper: MovieDbMapper
) : MoviesRepository {

    override fun getMovies(expression: String) = flow {
        val response = networkClient.doRequest(MovieSearchRequest(expression))
        when (response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                with(response as MovieSearchResponse) {
                    val data = results.map {
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description
                        )
                    }
                    saveMoviesToDb(data)
                    emit(Resource.Success(data))
                }
            }

            else -> emit(Resource.Error("Ошибка сервера"))
        }
    }

    override fun getMovieDetails(movieId: String) = flow {
        val response = networkClient.doRequest(MovieDetailsSearchRequest(movieId))
        when (response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                emit(
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
                )
            }

            else -> emit(Resource.Error("Ошибка сервера"))
        }
    }

    override fun getMovieFullCast(movieId: String) = flow {
        val response = networkClient.doRequest(MovieFullCastRequest(movieId))
        when (response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> emit(Resource.Success(mapper.convert(response as MovieCastResponse)))
            else -> emit(Resource.Error("Ошибка сервера"))
        }
    }

    private suspend fun saveMoviesToDb(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            val moviesEntity = movies.map { movieDbMapper.map(it) }
            dao.deleteAll()
            dao.insertMovies(moviesEntity)
        }
    }


}