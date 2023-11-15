package br.com.movieapp.movie_search_feature.data.mapper

import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.util.toBackDropUrl
import br.com.movieapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map { searchResult ->
    MovieSearch(
        id = searchResult.id,
        voteAverage = searchResult.voteAverage,
        imageUrl = searchResult.posterPath?.toPostUrl()
            ?: searchResult.backdropPath?.toBackDropUrl()
            ?: ""
    )
}