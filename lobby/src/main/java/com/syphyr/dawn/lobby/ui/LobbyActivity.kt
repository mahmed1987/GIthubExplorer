package com.syphyr.dawn.lobby.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.syphyr.dawn.githubexplorer.common.destinations.TopLevelDestinations
import com.syphyr.dawn.githubexplorer.common.state.AppState
import com.syphyr.dawn.githubexplorer.common.state.rememberAppState
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import com.syphyr.dawn.lobby.navigation.appNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LobbyActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GithubExplorerTheme() {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          val appState = rememberAppState()
          var showedSplash by rememberSaveable {
            mutableStateOf(false)
          }
          if (!showedSplash) {
            SplashScreen() {
              showedSplash = true
            }
          } else {
            MainAppScaffold(appState)
          }
        }
      }
    }
  }
}

@Composable
fun MainAppScaffold(appState: AppState) {
  val backstackEntry by appState.navController.currentBackStackEntryAsState()
  val currentScreen = backstackEntry?.destination?.route ?: "home/feed"
  Scaffold(

    scaffoldState = appState.scaffoldState
  ) { innerPaddingModifier ->
    NavHost(
      navController = appState.navController,
      startDestination = TopLevelDestinations.HOME_ROUTE,
      modifier = Modifier.padding(innerPaddingModifier)
    ) {
      appNavGraph(onRepositoryClicked = appState::navigateToRepositoryDetails, onBackPress = appState::upPress)
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GithubExplorerTheme {
    Greeting("Android")
  }
}