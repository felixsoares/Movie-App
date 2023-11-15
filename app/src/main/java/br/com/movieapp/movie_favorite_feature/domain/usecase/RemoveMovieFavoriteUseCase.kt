package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface RemoveMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(
        val movie: Movie
    )
}

class RemoveMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : RemoveMovieFavoriteUseCase {

    override suspend fun invoke(params: RemoveMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val delete = repository.delete(params.movie)
            emit(ResultData.Success(delete))
        }.flowOn(Dispatchers.IO)
    }

}