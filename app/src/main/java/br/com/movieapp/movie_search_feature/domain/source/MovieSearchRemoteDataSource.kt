package br.com.movieapp.movie_search_feature.domain.source

import br.com.movieapp.core.data.remote.response.SearchResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {

    fun getMovieSearchPagingSource(query: String): MovieSearchPagingSource

    suspend fun getSearchedMovies(query: String, page: Int): SearchResponse
}