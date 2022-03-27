package com.syphyr.dawn.githubexplorer.repositories.github

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.network.requestBlocking
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val githubWebServices: GithubWebServices) :
  GithubDataSource {
  override fun repositories(query: String): Either<Failure, List<RepositoryView>> {
    return when (val response = githubWebServices.repositories(query).requestBlocking()) {
      is Either.Right -> {
        Either.Right(response.b.repositories.map { it.toView() })
      }
      is Either.Left -> {
        response
      }
    }
  }

  override fun repository(id: Long): Either<RepositoryView, Failure> {
    TODO("Not yet implemented")
  }
}