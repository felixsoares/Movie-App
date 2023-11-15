package br.com.movieapp.movie_detail_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_detail_feature.data.repository.MovieDetailRepositoryImpl
import br.com.movieapp.movie_detail_feature.data.source.MovieDetailRemoteDataSourceImpl
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCase
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {

    @Provides
    @Singleton
    fun provideMovieDetailDataSource(
        service: MovieService
    ): MovieDetailRemoteDataSource = MovieDetailRemoteDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        dataSource: MovieDetailRemoteDataSource
    ): MovieDetailRepository = MovieDetailRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        movieDetailRepository: MovieDetailRepository
    ): GetMovieDetailUseCase = GetMovieDetailUseCaseImpl(movieDetailRepository)
}