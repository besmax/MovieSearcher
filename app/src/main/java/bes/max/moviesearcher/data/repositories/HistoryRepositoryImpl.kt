package bes.max.moviesearcher.data.repositories

import bes.max.moviesearcher.data.db.MovieDao
import bes.max.moviesearcher.data.db.MovieEntity
import bes.max.moviesearcher.data.mappers.MovieDbMapper
import bes.max.moviesearcher.domain.api.HistoryRepository
import bes.max.moviesearcher.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(
    private val movieDao: MovieDao,
    private val movieDbMapper: MovieDbMapper
) : HistoryRepository {
    override fun getHistoryMovies(): Flow<List<Movie>> =
        movieDao.getMovies().map { movies -> convertFromMovieEntity(movies) }


    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> =
        movies.map { movieDbMapper.map(it) }

}