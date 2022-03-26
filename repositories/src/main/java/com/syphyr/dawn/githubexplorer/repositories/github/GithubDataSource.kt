package com.syphyr.dawn.githubexplorer.repositories.github

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import com.syphyr.dawn.githubexplorer.views.repositories.Repository

interface GithubDataSource {
  fun repositories(query: String): Either<List<Repository>, Failure>
  fun repository(id:Long): Either<Repository, Failure>
}