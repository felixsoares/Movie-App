package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.internal.wait
import javax.inject.Inject

interface GetMovieDetailUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>?, MovieDetail?>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val repository: MovieDetailRepository
) : GetMovieDetailUseCase {

    override suspend operator fun invoke(
        params: GetMovieDetailUseCase.Params
    ): Flow<ResultData<Pair<Flow<PagingData<Movie>>?, MovieDetail?>>> = flow {
        emit(ResultData.Loading)
        try {
            val movieDetail = repository.getMovieDetail(params.movieId)
            val pageConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
            val movieSimilar = repository.getMovieSimilar(pageConfig, params.movieId)
            emit(ResultData.Success(Pair(movieSimilar, movieDetail)))
        } catch (ioException: Exception) {
            emit(ResultData.Error(ioException))
        } catch (httpException: Exception) {
            emit(ResultData.Error(httpException))
        }
    }.flowOn(Dispatchers.IO)
}