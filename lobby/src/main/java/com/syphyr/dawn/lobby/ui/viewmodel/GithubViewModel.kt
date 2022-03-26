package com.syphyr.dawn.lobby.ui.viewmodel

import androidx.lifecycle.*
import com.syphyr.dawn.githubexplorer.business.base.Args
import com.syphyr.dawn.githubexplorer.business.base.None
import com.syphyr.dawn.githubexplorer.business.base.StringArg
import com.syphyr.dawn.githubexplorer.business.github.SearchRepository
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
  private val searchRepository: SearchRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
  val uiState: LiveData<UiState> get() = _uiState

  init {
    _uiState.value = UiState.Error(Failure.NoResult)
  }

  fun searchRepositories(query: String) {
    viewModelScope.launch {
      _uiState.value = UiState.Loading
      val result = searchRepository(StringArg(query))
      result.either(::handleFailure) {
        _uiState.value = UiState.Success(it)
      }
    }
  }

  fun handleFailure(failure: Failure) {
    _uiState.value = UiState.Error(failure)
  }

  sealed class UiState {
    object Loading : UiState()
    class Success(val data: List<Repository>) : UiState()
    class Error(val failure: Failure) : UiState()
  }

}
