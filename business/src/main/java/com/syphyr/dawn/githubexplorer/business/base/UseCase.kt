package com.syphyr.dawn.githubexplorer.business.base

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<in Input, out Output>() where Input : Args {
  abstract suspend fun run(param: Input): Either<Failure, Output>
  suspend operator fun invoke(params: Input): Either<Failure, Output> {
    return withContext(Dispatchers.IO)
    {
      val result = run(params)
      withContext(Dispatchers.Main)
      {
        result
      }
    }
  }
}

open class Args
class None : Args()
class StringArg(val input: String) : Args()