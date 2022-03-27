package com.syphyr.dawn.githubexplorer.common.system

sealed class Failure {
  class ApiError(val reason:String) : Failure()
  object EmptyResult : Failure()
}