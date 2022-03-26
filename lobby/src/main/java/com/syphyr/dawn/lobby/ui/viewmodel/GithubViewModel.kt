package com.syphyr.dawn.lobby.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.syphyr.dawn.githubexplorer.business.base.Args
import com.syphyr.dawn.githubexplorer.business.base.None
import com.syphyr.dawn.githubexplorer.business.base.StringArg
import com.syphyr.dawn.githubexplorer.business.github.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
  private val searchRepository: SearchRepository,
  private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
  init {
    fetchAsync(None())
  }

  override suspend fun fetch(input: Args) = searchRepository(input as StringArg)

}
