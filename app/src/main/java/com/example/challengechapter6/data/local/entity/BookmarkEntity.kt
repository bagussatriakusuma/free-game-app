package com.example.challengechapter6.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
class BookmarkEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "genre") val genre: String
)