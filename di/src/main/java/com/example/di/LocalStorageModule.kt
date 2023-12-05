package com.example.di

import android.content.Context
import com.example.data.local.dao.BookmarkDAO
import com.example.data.local.database.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BookmarkDatabase {
        return BookmarkDatabase.getInstance(context = context)
    }

    @Singleton
    @Provides
    fun provideBookmarkDAO(db: BookmarkDatabase): BookmarkDAO {
        return db.bookmarkDAO()
    }
}