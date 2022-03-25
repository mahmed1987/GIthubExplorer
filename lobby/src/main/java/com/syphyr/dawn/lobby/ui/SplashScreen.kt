package com.syphyr.dawn.lobby.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier = Modifier, onInitializationComplete: () -> Unit = {}) {
  Surface(color = MaterialTheme.colors.background) {
    Column(
      modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = com.syphyr.dawn.githubexplorer.common.R.drawable.ic_splash_logo),
        contentDescription = "Main Application Logo",
      )
      Spacer(modifier = Modifier.padding(5.dp))
      CircularProgressIndicator()
    }
  }

  val currentOnInitializationComplete by rememberUpdatedState(onInitializationComplete)
  /* Here if SplashScreen composable is called again with a different callback , we do not want the LaunchedEffect block to be launched again.
  * However once the LaunchedEffect block is complete , we'd like to invoke the latest 'updated' onInitializationComplete*/
  LaunchedEffect(Unit)
  {
    delay(2000)
    currentOnInitializationComplete()
  }

}

@Preview
@Composable
fun PreviewSplashScreen() {
  GithubExplorerTheme() {
    SplashScreen()
  }
}