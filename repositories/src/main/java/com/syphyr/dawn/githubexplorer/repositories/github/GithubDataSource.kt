package com.syphyr.dawn.githubexplorer.repositories.github

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView

interface GithubDataSource {
  fun repositories(query: String): Either<Failure, List<RepositoryView>>
  fun repository(id: Long): Either<RepositoryView, Failure>
}