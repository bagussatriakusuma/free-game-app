package com.example.challengechapter6.di

import android.app.Application
import android.content.Context
import com.example.challengechapter6.data.local.dao.BookmarkDAO
import com.example.challengechapter6.data.local.datastore.DatastoreManager
import com.example.challengechapter6.data.remote.service.AuthAPI
import com.example.challengechapter6.data.remote.service.MainAPI
import com.example.challengechapter6.data.repository.local.LocalRepository
import com.example.challengechapter6.data.repository.remote.RemoteRepository
import com.example.challengechapter6.domain.AccountRepository
import com.example.challengechapter6.domain.AuthRepository
import com.example.challengechapter6.domain.BookmarkRepository
import com.example.challengechapter6.domain.MainRepository
import com.example.challengechapter6.domain.TokenRepository
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
    ): LocalRepository {
        return LocalRepository(
            bookmarkDAO
        )
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(
        datastore: DatastoreManager,
        apiAuth: AuthAPI,
        apiMain: MainAPI
    ): RemoteRepository {
        return RemoteRepository(
            datastore,
            apiAuth,
            apiMain
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(remoteRepository: RemoteRepository): AuthRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideAccountRepository(remoteRepository: RemoteRepository): AccountRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideTokenRepository(remoteRepository: RemoteRepository): TokenRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideMainRepository(remoteRepository: RemoteRepository): MainRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideBookmarkRepository(localRepository: LocalRepository): BookmarkRepository {
        return localRepository
    }
}