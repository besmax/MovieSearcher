package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.domain.models.MovieFullCast
import bes.max.moviesearcher.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(expression: String): Flow<Resource<List<Movie>>>

    fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>

    fun getMovieFullCast(movieId: String): Flow<Resource<MovieFullCast>>
}