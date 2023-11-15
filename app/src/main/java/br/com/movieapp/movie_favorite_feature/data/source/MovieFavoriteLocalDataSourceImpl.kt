package br.com.movieapp.movie_favorite_feature.data.source

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_favorite_feature.data.mapper.toMovieEntity
import br.com.movieapp.movie_favorite_feature.data.mapper.toMovies
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieFavoriteLocalDataSource {

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies().map {
            it.toMovies()
        }
    }

    override suspend fun insert(movie: Movie) {
        movieDao.insert(movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        movieDao.delete(movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.isFavorite(movieId) != null
    }
}