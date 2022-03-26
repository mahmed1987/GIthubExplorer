package com.syphyr.dawn.lobby.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.syphyr.dawn.githubexplorer.common.destinations.Screens
import com.syphyr.dawn.githubexplorer.common.destinations.TopLevelDestinations
import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.views.repositories.Repository
import com.syphyr.dawn.lobby.ui.screens.RepositoryDetail
import com.syphyr.dawn.lobby.ui.screens.RepositoryLibrary

fun NavGraphBuilder.appNavGraph(
  onRepositoryClicked: (Long, NavBackStackEntry) -> Unit,
) {
  navigation(
    route = TopLevelDestinations.HOME_ROUTE,
    startDestination = Screens.REPOSITORY_LIBRARY.route
  ) {
    addHomeGraph(onRepositoryClicked = onRepositoryClicked)
  }
}


fun NavGraphBuilder.addHomeGraph(
  modifier: Modifier = Modifier,
  onRepositoryClicked: (Long, NavBackStackEntry) -> Unit,
) {
  composable(Screens.REPOSITORY_LIBRARY.route) { from ->
    RepositoryLibrary(modifier, onRepositoryClicked = { id -> onRepositoryClicked(id, from) })
  }
  composable(Screens.REPOSITORY_DETAIL.route) { from ->
    val repositoryDetailsJson = from.arguments?.getString("selectedRepo") ?: "nothing"
    RepositoryDetail(Repository(32, repositoryDetailsJson))
  }

}