package com.example.challengechapter6.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challengechapter6.data.local.entity.BookmarkEntity

@Dao
interface BookmarkDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmark")
    fun getBookmarkedGames(): LiveData<List<BookmarkEntity>>

    @Delete
    fun deleteBookmarkedGames(bookmarkEntity: BookmarkEntity)
}
