package com.example.data.locale

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table_name")
data class MoviesCacheModel(
    @PrimaryKey
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "rating") val rating: Double,
)