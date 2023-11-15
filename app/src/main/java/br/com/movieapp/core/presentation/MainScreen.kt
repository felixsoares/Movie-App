package br.com.movieapp.core.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.movieapp.core.presentation.navigation.BottomNavigationBar
import br.com.movieapp.core.presentation.navigation.NavigationGraph

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val viewModel: MainScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            if (viewModel.uiState.titleName != 0) {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = viewModel.uiState.titleName))
                    },
                    navigationIcon = if (viewModel.uiState.hasBackArrow) {
                        {
                            IconButton(
                                onClick = {
                                    navController.navigateUp()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    } else {
                        null
                    }
                )
            }
        },
        content = { innerPadding ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                setBottomBarVisibility = viewModel::setBottomBarVisibility,
                setPageTitle = viewModel::setPageTitle,
                setBackArrowEnable = viewModel::setBackArrowEnable
            )
        },
        bottomBar = {
            if (viewModel.uiState.isBottomBarVisible)
                BottomNavigationBar(navController = navController)
        }
    )
}