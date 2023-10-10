package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistoryMovies(): Flow<List<Movie>>
}