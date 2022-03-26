package com.syphyr.dawn.lobby.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.syphyr.dawn.githubexplorer.business.base.StringArg
import com.syphyr.dawn.githubexplorer.business.github.SearchRepository
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
  private val searchRepository: SearchRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
  val uiState: LiveData<UiState> get() = _uiState
  private val searchQueryFlow: MutableStateFlow<String> = MutableStateFlow("")

  init {
    _uiState.value = UiState.Error(Failure.NoResult)
    viewModelScope.launch {
      searchQueryFlow
        .debounce(500) // restricting abnormal amounts of access to the API.
        .filter { it.length > 3 } // minimum 3 characters should be present for the search API to proceed
        .collect { query ->
          Log.d("ViewModel", "Searching for $query")
          _searchRepositories(query)
        }
    }
  }

  fun searchRepositories(query: String) {
    searchQueryFlow.value = query
  }

  //
  private fun _searchRepositories(query: String) {
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
