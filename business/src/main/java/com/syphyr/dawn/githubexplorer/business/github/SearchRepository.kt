package com.syphyr.dawn.githubexplorer.business.github

import com.syphyr.dawn.githubexplorer.business.base.StringArg
import com.syphyr.dawn.githubexplorer.business.base.UseCase
import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.repositories.github.GithubDataSource
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val githubDataSource: GithubDataSource) :
  UseCase<StringArg, List<RepositoryView>>() {
  override suspend fun run(param: StringArg): Either<Failure, List<RepositoryView>> {
//    githubDataSource.repositories(param.input)
    return Either.Right(listOf(RepositoryView(
      1,
      "This is something",
      "",
      "Achi Repo hai",
      "Assembly",
      33,
      33,
      33,
      2.toDouble()
    ), RepositoryView(
      1,
      "This is something",
      "",
      "Achi Repo hai",
      "Assembly",
      33,
      33,
      33,
      2.toDouble()
    )))
  }
}