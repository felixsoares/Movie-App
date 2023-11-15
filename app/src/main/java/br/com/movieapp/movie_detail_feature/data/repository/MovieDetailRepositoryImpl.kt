package br.com.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return movieDetailRemoteDataSource.getMovieDetail(movieId)
    }

    override suspend fun getMovieSimilar(
        pagingConfig: PagingConfig,
        movieId: Int
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { movieDetailRemoteDataSource.getSimilarMoviePagingSource(movieId) }
        ).flow
    }
}