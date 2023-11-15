package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.movie_search_feature.data.mapper.toMovieSearch
import br.com.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {

    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchedMovies(query, pageNumber)
            val searchedMovies = response.results.toMovieSearch()
            LoadResult.Page(
                data = searchedMovies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (searchedMovies.isEmpty()) null else pageNumber + 1
            )
        } catch (ioException: Exception) {
            LoadResult.Error(ioException)
        } catch (httpException: Exception) {
            LoadResult.Error(httpException)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}