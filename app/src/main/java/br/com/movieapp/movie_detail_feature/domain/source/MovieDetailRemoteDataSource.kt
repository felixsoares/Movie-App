package br.com.movieapp.movie_detail_feature.domain.source

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.core.paging.MovieSimilarMoviePagingSource

interface MovieDetailRemoteDataSource {

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getMovieSimilar(page: Int, movieId: Int): MovieResponse

    fun getSimilarMoviePagingSource(movieId: Int): MovieSimilarMoviePagingSource

}