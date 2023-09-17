package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.domain.models.MovieFullCast
import bes.max.moviesearcher.util.Resource

interface MoviesRepository {

    fun getMovies(expression: String) : Resource<List<Movie>>

    fun getMovieDetails(movieId: String) : Resource<MovieDetails>

    fun getMovieFullCast(movieId: String) : Resource<MovieFullCast>
}