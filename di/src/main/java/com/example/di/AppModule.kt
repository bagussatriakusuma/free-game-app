package com.example.di

import android.app.Application
import android.content.Context
import com.example.data.local.dao.BookmarkDAO
import com.example.data.local.datastore.DatastoreManager
import com.example.data.remote.service.AuthAPI
import com.example.data.remote.service.MainAPI
import com.example.data.repository.local.LocalRepository
import com.example.data.repository.remote.RemoteRepository
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.BookmarkRepository
import com.example.domain.repository.MainRepository
import com.example.domain.repository.TokenRepository
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