package br.com.movieapp.movie_detail_feature.presentation.state

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState(
    val movieDetail: MovieDetail? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val results: Flow<PagingData<Movie>> = emptyFlow(),
)
