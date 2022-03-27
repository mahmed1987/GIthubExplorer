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
    return githubDataSource.repositories(param.input)
  }
}