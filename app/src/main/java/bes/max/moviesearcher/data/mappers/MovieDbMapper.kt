package bes.max.moviesearcher.data.mappers

import bes.max.moviesearcher.data.db.MovieEntity
import bes.max.moviesearcher.data.dto.MovieDto
import bes.max.moviesearcher.domain.models.Movie

class MovieDbMapper {

    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: Movie): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

}