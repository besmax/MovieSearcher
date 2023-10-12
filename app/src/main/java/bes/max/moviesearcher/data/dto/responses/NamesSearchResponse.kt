package bes.max.moviesearcher.data.dto.responses

import bes.max.moviesearcher.data.dto.PersonDto

class NamesSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<PersonDto>) : Response()
