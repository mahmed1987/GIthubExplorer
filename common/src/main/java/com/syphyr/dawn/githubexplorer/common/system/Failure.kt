package com.syphyr.dawn.githubexplorer.common.system

sealed class Failure {
  object NoResult : Failure()
}