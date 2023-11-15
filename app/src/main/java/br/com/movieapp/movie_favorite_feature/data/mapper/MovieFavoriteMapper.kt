package br.com.movieapp.movie_favorite_feature.data.mapper

import br.com.movieapp.core.data.local.entity.MovieEntity
import br.com.movieapp.core.domain.model.Movie

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.id,
        title = movieEntity.title,
        imageUrl = movieEntity.posterPath,
        voteAverage = movieEntity.voteAverage,
    )
}

fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    posterPath = imageUrl,
    voteAverage = voteAverage,
)