package com.syphyr.dawn.lobby.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.syphyr.dawn.githubexplorer.common.R
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import com.syphyr.dawn.githubexplorer.views.repositories.Repository
import com.syphyr.dawn.lobby.ui.viewmodel.GithubViewModel

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
    items(repositories) { repository ->
      RepositoryItem(repository)
    }
  }
}

@Composable
fun ShowLoadingUi() {
}

@Composable
fun RepositoryItem(repository: Repository) {
  Row() {
    CoilImage(
      imageUrl = "https://source.unsplash.com/pGM4sjt_BdQ",
      contentDescription = "Repository Image"
    )
    Spacer(modifier = Modifier.width(15.dp))
    Column() {
      Text(text = "Tetris", style = MaterialTheme.typography.subtitle1)
      Text(text = "This is a repo", style = MaterialTheme.typography.subtitle2)
    }
  }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(imageUrl: String, contentDescription: String) {
  Image(
    painter = rememberImagePainter(
      data = imageUrl,
      builder = {
        crossfade(true)
        placeholder(drawableResId = R.drawable.placeholder)
      }
    ),
    contentDescription = contentDescription,
    modifier = Modifier
      .size(50.dp)
      .clip(RoundedCornerShape(5.dp)),
    contentScale = ContentScale.Crop
  )
}

@Preview
@Composable
fun PreviewRepositoryItem() {
  GithubExplorerTheme {
    RepositoryItem(Repository(1, "Ahmed"))
  }
}

@Composable
fun ShowErrorUi(failure: Failure) {
  Column(
    modifier =Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
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

