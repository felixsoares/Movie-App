package br.com.movieapp.movie_favorite_feature.di

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.RemoveMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.RemoveMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteFeatureModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(
        movieDao: MovieDao
    ): MovieFavoriteLocalDataSource = MovieFavoriteLocalDataSourceImpl(movieDao)

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(
        dataSource: MovieFavoriteLocalDataSource
    ): MovieFavoriteRepository = MovieFavoriteRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(
        repository: MovieFavoriteRepositoryImpl
    ): AddMovieFavoriteUseCase = AddMovieFavoriteUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(
        repository: MovieFavoriteRepositoryImpl
    ): RemoveMovieFavoriteUseCase = RemoveMovieFavoriteUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(
        repository: MovieFavoriteRepositoryImpl
    ): GetMoviesFavoriteUseCase = GetMoviesFavoriteUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(
        repository: MovieFavoriteRepositoryImpl
    ): IsMovieFavoriteUseCase = IsMovieFavoriteUseCaseImpl(repository)
}