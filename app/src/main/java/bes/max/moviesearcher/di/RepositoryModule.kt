package bes.max.moviesearcher.di

import android.content.Context
import androidx.room.Room
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.db.MovieDao
import bes.max.moviesearcher.data.db.MovieDatabase
import bes.max.moviesearcher.data.mappers.MovieCastResponseToMovieFullCastMapper
import bes.max.moviesearcher.data.mappers.MovieDbMapper
import bes.max.moviesearcher.data.mappers.PersonDtoMapper
import bes.max.moviesearcher.data.repositories.HistoryRepositoryImpl
import bes.max.moviesearcher.data.repositories.MoviesRepositoryImpl
import bes.max.moviesearcher.data.repositories.NamesRepositoryImpl
import bes.max.moviesearcher.domain.api.HistoryRepository
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.api.NamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database").build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.getMovieDao()
    }

    @Provides
    fun provideMoviesRepository(
        networkClient: NetworkClient,
        dao: MovieDao,
        mapper: MovieCastResponseToMovieFullCastMapper,
        movieDbMapper: MovieDbMapper
    ): MoviesRepository {
        return MoviesRepositoryImpl(networkClient = networkClient, dao = dao, mapper = mapper, movieDbMapper = movieDbMapper)
    }

    @Provides
    fun provideMovieCastResponseToMovieFullCastMapper(): MovieCastResponseToMovieFullCastMapper {
        return MovieCastResponseToMovieFullCastMapper()
    }

    @Provides
    fun provideMovieDbMapper(): MovieDbMapper {
        return MovieDbMapper()
    }

    @Provides
    fun provideHistoryRepository(dao: MovieDao, mapper: MovieDbMapper): HistoryRepository {
        return HistoryRepositoryImpl(dao, mapper)
    }

    @Provides
    fun providePersonDtoMapper(): PersonDtoMapper {
        return PersonDtoMapper()
    }

    @Provides
    fun provideNamesRepository(networkClient: NetworkClient, mapper: PersonDtoMapper): NamesRepository {
        return NamesRepositoryImpl(networkClient, mapper)
    }

}