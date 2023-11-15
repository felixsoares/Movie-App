package br.com.movieapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie"
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
)
//val overview: String,
//val backdropPath: String,
//val voteAverage: Double,
//val releaseDate: String,
//val runtime: Int,
//val similar: List<MovieEntity>,
//val recommendations: List<MovieEntity>,
//val isFavorite: Boolean
