package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Movie

interface MoviesRepository {

    fun getMovies(expression: String) : List<Movie>
}