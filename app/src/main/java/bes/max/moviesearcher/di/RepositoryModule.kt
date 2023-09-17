package bes.max.moviesearcher.di

import bes.max.moviesearcher.data.MoviesRepositoryImpl
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.mappers.MovieCastResponseToMovieFullCastMapper
import bes.max.moviesearcher.domain.api.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

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