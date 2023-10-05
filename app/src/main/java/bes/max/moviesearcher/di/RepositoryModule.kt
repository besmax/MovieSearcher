package bes.max.moviesearcher.di

import android.content.Context
import androidx.room.Room
import bes.max.moviesearcher.data.MoviesRepositoryImpl
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.db.MovieDao
import bes.max.moviesearcher.data.db.MovieDatabase
import bes.max.moviesearcher.data.mappers.MovieCastResponseToMovieFullCastMapper
import bes.max.moviesearcher.domain.api.MoviesRepository
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
    fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao {
        return movieDatabase.getMovieDao()
    }

    @Provides
    fun provideMoviesRepository(
        networkClient: NetworkClient,
        mapper: MovieCastResponseToMovieFullCastMapper
    ): MoviesRepository {
        return MoviesRepositoryImpl(networkClient = networkClient, mapper = mapper)
    }

    @Provides
    fun provideMovieCastResponseToMovieFullCastMapper(): MovieCastResponseToMovieFullCastMapper {
        return MovieCastResponseToMovieFullCastMapper()
    }

}