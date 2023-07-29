package bes.max.moviesearcher.di

import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideNetworkClient() : NetworkClient {
        return RetrofitNetworkClient()
    }
}