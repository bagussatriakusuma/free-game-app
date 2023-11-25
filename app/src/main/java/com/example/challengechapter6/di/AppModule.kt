package com.example.challengechapter6.di

import android.app.Application
import android.content.Context
import com.example.data.local.dao.BookmarkDAO
import com.example.domain.AccountRepository
import com.example.domain.AuthRepository
import com.example.domain.BookmarkRepository
import com.example.domain.MainRepository
import com.example.domain.TokenRepository
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
    fun provideLocalRepository(
        bookmarkDAO: BookmarkDAO
    ): com.example.data.repository.local.LocalRepository {
        return com.example.data.repository.local.LocalRepository(
            bookmarkDAO
        )
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(
        datastore: com.example.data.local.datastore.DatastoreManager,
        apiAuth: com.example.data.remote.service.AuthAPI,
        apiMain: com.example.data.remote.service.MainAPI
    ): com.example.data.repository.remote.RemoteRepository {
        return com.example.data.repository.remote.RemoteRepository(
            datastore,
            apiAuth,
            apiMain
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(remoteRepository: com.example.data.repository.remote.RemoteRepository): AuthRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideAccountRepository(remoteRepository: com.example.data.repository.remote.RemoteRepository): AccountRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideTokenRepository(remoteRepository: com.example.data.repository.remote.RemoteRepository): TokenRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideMainRepository(remoteRepository: com.example.data.repository.remote.RemoteRepository): MainRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideBookmarkRepository(localRepository: com.example.data.repository.local.LocalRepository): BookmarkRepository {
        return localRepository
    }
}