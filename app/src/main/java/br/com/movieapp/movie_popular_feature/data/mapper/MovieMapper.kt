package br.com.movieapp.movie_popular_feature.data.mapper

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.toBackDropUrl
import br.com.movieapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map { movieResult ->
    Movie(
        movieResult.id,
        movieResult.title,
        movieResult.voteAverage,
        movieResult.posterPath?.toPostUrl()
            ?: movieResult.backdropPath?.toBackDropUrl()
            ?: ""
    )
}