package bes.max.moviesearcher.di

import android.content.Context
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.network.MovieApiService
import bes.max.moviesearcher.data.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideNetworkClient(
        @ApplicationContext context: Context,
        imdbService: MovieApiService
    ): NetworkClient {
        return RetrofitNetworkClient(
            imdbService,
            context,
        )
    }

    @Provides
    fun provideImdbService(): MovieApiService {
        val imdbBaseUrl = "https://imdb-api.com"

        val retrofit = Retrofit.Builder()
            .baseUrl(imdbBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieApiService::class.java)
    }

}