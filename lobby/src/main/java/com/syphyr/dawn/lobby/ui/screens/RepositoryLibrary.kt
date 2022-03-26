package com.syphyr.dawn.lobby.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.syphyr.dawn.githubexplorer.common.R
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import com.syphyr.dawn.githubexplorer.views.Repository
import com.syphyr.dawn.lobby.ui.viewmodel.GithubViewModel

@Composable
fun RepositoryLibrary(
  modifier: Modifier = Modifier,
  onRepositoryClicked: (Long) -> Unit,
  viewModel: GithubViewModel = hiltViewModel()
) {
  Surface(color = MaterialTheme.colors.background) {
    Column(Modifier.padding(20.dp, 32.dp, 20.dp, 0.dp)) {
      Text(
        text = stringResource(id = R.string.repository_library),
        style = MaterialTheme.typography.h6,
      )
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(20.dp)
      )
      SearchBox()
    }

    val uiState by viewModel.uiState.observeAsState()
    uiState?.let { state ->
      when (state) {
        is GithubViewModel.UiState.Error -> ShowErrorUi(state.failure)
        GithubViewModel.UiState.Loading -> ShowLoadingUi()
        is GithubViewModel.UiState.Success -> ShowSuccessUi(state.data)
      }
    }
  }
}

@Composable
fun ShowSuccessUi(repositories: List<Repository>) {

}

@Composable
fun ShowLoadingUi() {
}

@Composable
fun ShowErrorUi(failure: Failure) {


}

@Composable
fun OnSuccess(repositories: List<Repository> = listOf()) {

}

@Composable
fun SearchBox() {
  var password by rememberSaveable { mutableStateOf("") }

  TextField(
    modifier = Modifier.fillMaxWidth(),
    value = password,
    onValueChange = { password = it },
    label = { Text("Enter password") },
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewRepositoryLibrary() {
  GithubExplorerTheme {
    RepositoryLibrary(onRepositoryClicked = {})
  }
}

