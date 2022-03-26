package com.syphyr.dawn.githubexplorer.business.github

import com.syphyr.dawn.githubexplorer.business.base.StringArg
import com.syphyr.dawn.githubexplorer.business.base.UseCase
import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.repositories.github.GithubDataSource
import com.syphyr.dawn.githubexplorer.views.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val githubDataSource: GithubDataSource) :
  UseCase<StringArg, List<Repository>>() {
  override suspend fun run(param: StringArg): Either<Failure, List<Repository>> {
    githubDataSource.repositories(param.input)
    return Either.Left(Failure.Android("a", 1))
  }
}