package br.com.movieapp.movie_popular_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUserCase
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUserCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource =
        MoviePopularRemoteDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideMoviePopularRepository(dataSource: MoviePopularRemoteDataSource): MoviePopularRepository =
        MoviePopularRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideMoviesPopularUseCase(repository: MoviePopularRepositoryImpl): GetPopularMovieUserCase =
        GetPopularMovieUserCaseImpl(repository)
}