package com.syphyr.dawn.githubexplorer.common.state

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import com.syphyr.dawn.githubexplorer.common.destinations.Screens
import com.syphyr.dawn.githubexplorer.common.system.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
  scaffoldState: ScaffoldState = rememberScaffoldState(),
  navController: NavHostController = rememberNavController(),
  snackbarManager: SnackbarManager = SnackbarManager,
  resources: Resources = resources(),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
  remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
    AppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
  }

/**
 * Responsible for holding state related to [GithubExplorer] and containing UI-related logic.
 */
@Stable
class AppState(
  val scaffoldState: ScaffoldState,
  val navController: NavHostController,
  private val snackbarManager: SnackbarManager,
  private val resources: Resources,
  private val coroutineScope: CoroutineScope
) {

  //Process snackbars coming from Snackbar Manager
  init {
    coroutineScope.launch {
      SnackbarManager.messages.collect { currentMessages ->
        if (currentMessages.isNotEmpty()) {
          val message = currentMessages[0]
          val text = resources.getText(message.messageId)

          //Display the snack on the screen. `showSnackbar` is a function
          //that suspends until the snackbar disappears from the screen
          scaffoldState.snackbarHostState.showSnackbar(text.toString())
          //once the snack is gone or dismissed , notify the Snackbar Manager
          SnackbarManager.setMessageShown(message.id)
        }
      }
    }

  }


  fun navigateToRepositoryDetails(repositoryDetailsJson: String) {
    navController.navigate(
      Screens.REPOSITORY_DETAIL.route.replace(
        "{selectedRepo}",
        repositoryDetailsJson
      )
    )
  }
}




private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
  return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

private val NavGraph.startDestination: NavDestination?
  get() = findNode(startDestinationId)

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
  LocalConfiguration.current
  return LocalContext.current.resources
}




