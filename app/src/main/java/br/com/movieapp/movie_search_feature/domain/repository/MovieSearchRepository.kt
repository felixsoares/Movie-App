package br.com.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {

    suspend fun getaSearchMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>

}