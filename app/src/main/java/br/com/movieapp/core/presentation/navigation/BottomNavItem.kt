package br.com.movieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.movieapp.R
import br.com.movieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String,
    val stringResourceTitle: Int
) {
    object Popular : BottomNavItem(
        "movie_popular_screen",
        Icons.Default.Movie,
        "Filmes populares",
        R.string.popular_movies
    )

    object Search : BottomNavItem(
        "movie_search_screen",
        Icons.Default.Search,
        "Pesquisar",
        R.string.search_movies
    )

    object Favorite : BottomNavItem(
        "movie_favorite_screen",
        Icons.Default.Favorite,
        "Favoritos",
        R.string.favorite_movies
    )

    object Detail : BottomNavItem(
        "movie_detail_screen?$MOVIE_DETAIL_ARGUMENT_KEY={$MOVIE_DETAIL_ARGUMENT_KEY}",
        Icons.Default.Details,
        "Detalhes",
        R.string.detail_movie
    ) {
        fun passMovieId(movieId: Int): String {
            return "movie_detail_screen?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
        }
    }
}
