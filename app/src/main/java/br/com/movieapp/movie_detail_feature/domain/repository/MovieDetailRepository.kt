package br.com.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int) : MovieDetail

    suspend fun getMovieSimilar(pagingConfig: PagingConfig, movieId: Int) : Flow<PagingData<Movie>>

}