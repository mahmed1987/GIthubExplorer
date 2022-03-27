package com.syphyr.dawn.lobby.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.gson.Gson
import com.syphyr.dawn.githubexplorer.common.destinations.Screens
import com.syphyr.dawn.githubexplorer.common.destinations.TopLevelDestinations
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView
import com.syphyr.dawn.lobby.ui.screens.RepositoryDetail
import com.syphyr.dawn.lobby.ui.screens.RepositoryLibrary

fun NavGraphBuilder.appNavGraph(
  onRepositoryClicked: (String) -> Unit,
  onBackPress: () -> Unit
) {
  navigation(
    route = TopLevelDestinations.HOME_ROUTE,
    startDestination = Screens.REPOSITORY_LIBRARY.route
  ) {
    addHomeGraph(onRepositoryClicked = onRepositoryClicked, backPress = onBackPress)
  }
}


fun NavGraphBuilder.addHomeGraph(
  modifier: Modifier = Modifier,
  onRepositoryClicked: (String) -> Unit,
  backPress: () -> Unit
) {
  composable(Screens.REPOSITORY_LIBRARY.route) {
    RepositoryLibrary( onRepositoryClicked)
  }
  composable(Screens.REPOSITORY_DETAIL.route) { from ->
    val repositoryDetailsJson = from.arguments?.getString("selectedRepo")
    repositoryDetailsJson?.let {
      val repositoryView = Gson().fromJson(it, RepositoryView::class.java)
      RepositoryDetail(repositoryView, backPress)
    }

  }

}