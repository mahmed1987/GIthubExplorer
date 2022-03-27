package com.syphyr.dawn.lobby.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syphyr.dawn.githubexplorer.common.theme.GithubExplorerTheme
import com.syphyr.dawn.githubexplorer.common.theme.Whisper
import com.syphyr.dawn.lobby.R
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView

typealias Strings = com.syphyr.dawn.githubexplorer.common.R.string

@Composable
fun RepositoryDetail(
  repositoryView: RepositoryView,
  onBackPress: () -> Unit,

  ) {
  Surface(color = MaterialTheme.colors.background) {
    Column() {
      Spacer(modifier = Modifier.height(32.dp))
      IconButton(onClick = { onBackPress() }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_arrow_back_24dp),
          contentDescription = "Back Button"
        )
      }
      Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        with(repositoryView) {
          RemoteImage(imageUrl = imageUrl, size = 100.dp)
          Spacer(modifier = Modifier.height(14.dp))
          Text(text = name, style = MaterialTheme.typography.subtitle1)
          Spacer(modifier = Modifier.height(7.dp))
          Text(text = language, style = MaterialTheme.typography.subtitle2)
          RepositoryStatsTable(repositoryView)
        }
      }
    }


  }
}

@Composable
fun RepositoryStatsTable(repositoryView: RepositoryView) {

  Column(
    Modifier
      .fillMaxWidth()
      .padding(20.dp, 30.dp, 20.dp, 0.dp)
      .border(1.dp, Whisper, RoundedCornerShape(6.dp))
  ) {
    with(repositoryView) {
      Column(Modifier.padding(20.dp, 18.dp, 20.dp, 21.dp)) {
        LabelValueRow(stringResource(id = Strings.forks), forksCount.toString())
        PaddingLine()
        LabelValueRow(stringResource(id = Strings.issues), issuesCount.toString())
        PaddingLine()
        LabelValueRow(stringResource(id = Strings.starred_by), starredBy.toString())
        PaddingLine()
        LabelValueRow(stringResource(id = Strings.latest_release_version), lastReleaseVersion.toString())
      }

    }
  }
}

@Preview
@Composable
fun PreviewLabelValueRow() {
  LabelValueRow(label = "Asd", value = "Asd")
}

@Composable
fun LabelValueRow(label: String, value: String) {
  Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
    Text(
      text = label,
      style = MaterialTheme.typography.subtitle2
    )
    Text(
      text = value,
      style = MaterialTheme.typography.subtitle2
    )
  }
}

@Composable
fun PaddingLine() {
  Divider(
    color = Whisper,
    thickness = 1.dp,
    modifier = Modifier.padding(top = 18.dp, bottom = 15.dp)
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewRepositoryDetail() {
  GithubExplorerTheme() {
    RepositoryDetail(
      repositoryView = RepositoryView(
        1,
        "This is something",
        "",
        "Achi Repo hai",
        "Assembly",
        33,
        33,
        33,
        2.toDouble()
      )
    ) {

    }
  }

}