package bes.max.moviesearcher.model

class MovieResponse(val searchType: String,
                    val expression: String,
                    val results: List<Movie>) {

}