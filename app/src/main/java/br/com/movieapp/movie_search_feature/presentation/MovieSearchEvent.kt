package br.com.movieapp.movie_search_feature.presentation

sealed class MovieSearchEvent {
    data class EnteredQuery(val query: String) : MovieSearchEvent()
}
