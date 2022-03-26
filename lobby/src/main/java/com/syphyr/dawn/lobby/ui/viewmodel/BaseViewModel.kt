package com.syphyr.dawn.lobby.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syphyr.dawn.githubexplorer.business.base.Args
import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.Data
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
  private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
  val uiState: LiveData<UiState> get() = _uiState

  //Single point of failure
  protected fun handleFailure(failure: Failure) {
    _uiState.value = UiState.Error(failure)
  }

  //a template function for remote operations
  fun fetchAsync(input: Args) {
    viewModelScope.launch {
      _uiState.value = UiState.Loading
      val result = fetch(input)
      result.either(::handleFailure) {
        _uiState.value = UiState.Success(it)
      }
    }
  }

  abstract suspend fun fetch(input: Args): Either<Failure, List<Data>>

  sealed class UiState {
    object Loading : UiState()
    class Success<T : Any>(val data: T) : UiState()
    class Error(val failure: Failure) : UiState()
  }

}