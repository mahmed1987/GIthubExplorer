package com.syphyr.dawn.githubexplorer.common.system

sealed class Failure {
  object ApiError : Failure()
  object EmptyResult : Failure()
}