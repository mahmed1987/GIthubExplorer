package com.syphyr.dawn.githubexplorer.repositories.github

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val githubWebServices: GithubWebServices) :
  GithubDataSource {
  override fun repositories(query: String): Either<List<Repository>, Failure> {
    TODO("Not yet implemented")
  }

  override fun repository(id: Long): Either<Repository, Failure> {
    TODO("Not yet implemented")
  }
}