package com.syphyr.dawn.lobby.ui.screens

import android.graphics.Color
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.syphyr.dawn.githubexplorer.common.R
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import com.syphyr.dawn.githubexplorer.views.Repository
import com.syphyr.dawn.lobby.ui.viewmodel.GithubViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

@Composable
fun RepositoryLibrary(
  modifier: Modifier = Modifier,
  onRepositoryClicked: (Long) -> Unit,
  viewModel: GithubViewModel = hiltViewModel()
) {
  Surface(color = MaterialTheme.colors.background) {
    Column(
      Modifier
        .padding(20.dp, 32.dp, 20.dp, 0.dp)
        .fillMaxSize()
    ) {
      Text(
        text = stringResource(id = R.string.repository_library),
        style = MaterialTheme.typography.h6,
      )
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(20.dp)
      )
      var currentSearchQuery by rememberSaveable { mutableStateOf("") }
      SearchBox(currentSearchQuery) { query ->
        currentSearchQuery = query
        viewModel.searchRepositories(query)
      }
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Content(viewModel)
      }
    }
  }

}

@Composable
fun ColumnScope.Content(viewModel: GithubViewModel) {
  val uiState by viewModel.uiState.observeAsState()
  uiState?.let { state ->
    when (state) {
      is GithubViewModel.UiState.Error -> ShowErrorUi(state.failure)
      GithubViewModel.UiState.Loading -> ShowLoadingUi()
      is GithubViewModel.UiState.Success -> ShowSuccessUi(state.data)
    }
  }
}

@Composable
fun ColumnScope.ShowSuccessUi(repositories: List<Repository>) {
  LazyColumn {
    item {
      RepositoryItem()
    }
  }
}

@Composable
fun ShowLoadingUi() {
}

@Composable
fun RepositoryItem() {
  Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter =  rememberImagePainter(
        data = "https://source.unsplash.com/pGM4sjt_BdQ",
        builder = {
          crossfade(true)
          placeholder(drawableResId = R.drawable.placeholder)
        }
      ),
      contentDescription = "RepositoryImage",
      modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .size(60.dp)
        .clip(RoundedCornerShape(10.dp))

    )
    Spacer(modifier = Modifier.width(15.dp))
    Column() {
      Text(text = "Tetris", style = MaterialTheme.typography.subtitle1)
      Text(text = "This is a repo", style = MaterialTheme.typography.subtitle2)
    }
  }

}

@Preview
@Composable
fun PreviewRepositoryItem() {
  GithubExplorerTheme {
    RepositoryItem()
  }
}

@Composable
fun ShowErrorUi(failure: Failure) {

  Image(
    painter = painterResource(id = com.syphyr.dawn.lobby.R.drawable.ic_no_results),
    contentDescription = "Empty screen"
  )
  Spacer(modifier = Modifier.height(30.dp))
  Text(
    text = stringResource(id = R.string.a_little_empty),
    style = MaterialTheme.typography.subtitle1
  )
  Spacer(modifier = Modifier.height(10.dp))
  Text(
    text = stringResource(id = R.string.empty_list_caption),
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.subtitle2
  )
}

@Composable
fun OnSuccess(repositories: List<Repository> = listOf()) {

}

@Composable
fun SearchBox(value: String = "", onValueChange: (String) -> Unit) {
  TextField(
    modifier = Modifier.fillMaxWidth(),
    value = value,
    onValueChange = onValueChange,
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

