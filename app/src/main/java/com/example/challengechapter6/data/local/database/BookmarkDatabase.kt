package com.example.challengechapter6.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengechapter6.data.local.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 3)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDAO(): _root_ide_package_.com.example.challengechapter6.data.local.dao.BookmarkDAO

    companion object {
        private const val DB_NAME = "bookmark.db"

        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getInstance(context: Context): BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): BookmarkDatabase {
            return Room.databaseBuilder(context, BookmarkDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}