package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.main.Bookmark

@Entity(tableName = "bookmark")
class BookmarkEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "genre") val genre: String
)

fun Bookmark.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        thumbnail = thumbnail,
        title = title,
        genre = genre
    )
}

fun BookmarkEntity.toBookmark(): Bookmark {
    return Bookmark(
        id = id,
        thumbnail = thumbnail,
        title = title,
        genre = genre
    )
}