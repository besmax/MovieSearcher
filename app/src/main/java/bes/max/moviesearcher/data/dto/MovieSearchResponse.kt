package bes.max.moviesearcher.data.dto

class MovieSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<MovieDto>) :Response() {

}