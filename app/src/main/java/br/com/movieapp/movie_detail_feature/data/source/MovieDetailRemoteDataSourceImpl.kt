package br.com.movieapp.movie_detail_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.core.paging.MovieSimilarMoviePagingSource
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import javax.inject.Inject

class MovieDetailRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailRemoteDataSource {

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        val response = service.getMovieDetails(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetail(
            id = response.id,
            title = response.title,
            genres = genres,
            overview = response.overview,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath ?: response.posterPath,
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMovieSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getMoviesSimilar(page, movieId)
    }

    override fun getSimilarMoviePagingSource(movieId: Int): MovieSimilarMoviePagingSource {
        return MovieSimilarMoviePagingSource(this, movieId)
    }

}