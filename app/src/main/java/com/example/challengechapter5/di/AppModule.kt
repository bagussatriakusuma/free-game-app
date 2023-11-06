package com.example.challengechapter5.di

import android.app.Application
import android.content.Context
import com.example.challengechapter5.data.remote.service.AuthAPI
import com.example.challengechapter5.datastore.DatastoreManager
import com.example.challengechapter5.domain.RemoteRepository
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(
        datastore: DatastoreManager,
        api: AuthAPI
    ): RemoteRepository {
        return RemoteRepository(
            datastore = datastore,
            api = api
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(remoteRepository: RemoteRepository): AuthRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideTokenRepository(remoteRepository: RemoteRepository): TokenRepository {
        return remoteRepository
    }
}