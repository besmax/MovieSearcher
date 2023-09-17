package bes.max.moviesearcher.data.mappers

import bes.max.moviesearcher.data.dto.responses.MovieCastResponse
import bes.max.moviesearcher.domain.models.MovieCastPerson
import bes.max.moviesearcher.domain.models.MovieFullCast

class MovieCastResponseToMovieFullCastMapper {

    fun convert(response: MovieCastResponse): MovieFullCast {
        return MovieFullCast(
            imdbId = response.imDbId,
            fullTitle = response.fullTitle,
            directors = response.directors.items.map {
                MovieCastPerson(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = null
                )
            },
            writers = response.writers.items.map {
                MovieCastPerson(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = null
                )
            },
            actors = response.actors.map {
                MovieCastPerson(
                    id = it.id,
                    name = it.name,
                    description = it.asCharacter,
                    image = it.image,
                )
            },
            others = response.others.flatMap {
                it.items.map {
                    MovieCastPerson(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        image = null
                    )
                }
            }
        )
    }
}