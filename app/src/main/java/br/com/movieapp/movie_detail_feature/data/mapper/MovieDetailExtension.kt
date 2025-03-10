package br.com.movieapp.movie_detail_feature.data.mapper

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail

fun MovieDetail.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        imageUrl = backdropPathUrl.toString()
    )
}