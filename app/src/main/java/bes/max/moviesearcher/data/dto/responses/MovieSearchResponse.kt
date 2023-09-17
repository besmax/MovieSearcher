package bes.max.moviesearcher.data.dto.responses

import bes.max.moviesearcher.data.dto.MovieDto

class MovieSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<MovieDto>) : Response() {

}