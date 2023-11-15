package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY
import br.com.movieapp.movie_detail_feature.presentation.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.presentation.MovieDetailViewModel
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.movie_search_feature.presentation.MovieSearchEvent
import br.com.movieapp.movie_search_feature.presentation.MovieSearchScreen
import br.com.movieapp.movie_search_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier,
    setBottomBarVisibility: (Boolean) -> Unit,
    setPageTitle: (Int) -> Unit,
    setBackArrowEnable: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Popular.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Popular.route) {
            setPageTitle(BottomNavItem.Popular.stringResourceTitle)
            setBottomBarVisibility(true)
            setBackArrowEnable(false)

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToMovieDetailScreen = { movieId ->
                    navController.navigate(BottomNavItem.Detail.passMovieId(movieId))
                }
            )
        }

        composable(BottomNavItem.Search.route) {
            setPageTitle(BottomNavItem.Search.stringResourceTitle)
            setBottomBarVisibility(true)
            setBackArrowEnable(false)

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = { onEvent(it) },
                onFetch = { onFetch(it) },
                navigateToMovieDetailScreen = { movieId ->
                    navController.navigate(BottomNavItem.Detail.passMovieId(movieId))
                }
            )
        }

        composable(BottomNavItem.Favorite.route) {
            setPageTitle(BottomNavItem.Favorite.stringResourceTitle)
            setBottomBarVisibility(true)
            setBackArrowEnable(false)

            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetail = { movieId ->
                    navController.navigate(BottomNavItem.Detail.passMovieId(movieId))
                }
            )
        }

        composable(BottomNavItem.Detail.route, arguments = listOf(
            navArgument(MOVIE_DETAIL_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = 0
            }
        )) {
            setPageTitle(BottomNavItem.Detail.stringResourceTitle)
            setBottomBarVisibility(false)
            setBackArrowEnable(true)

            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val getMovieDetail = viewModel::getMovieDetail

            val onAddFavorite = viewModel::onAddFavorite
            val checkIfMovieIsFavorite = viewModel::checkIfMovieIsFavorite

            MovieDetailScreen(
                movieId = it.arguments?.getInt(MOVIE_DETAIL_ARGUMENT_KEY),
                uiState = uiState,
                getMovieDetail = getMovieDetail,
                onAddFavorite = onAddFavorite,
                checkIfMovieIsFavorite = checkIfMovieIsFavorite,
                navigateToMovieDetailScreen = { movieId ->
                    navController.navigate(BottomNavItem.Detail.passMovieId(movieId))
                }
            )

        }
    }
}