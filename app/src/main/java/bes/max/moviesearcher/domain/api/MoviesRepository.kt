package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieDetails

interface MoviesRepository {

    fun getMovies(expression: String) : List<Movie>

    fun getMovieDetails(movieId: String) : MovieDetails
}