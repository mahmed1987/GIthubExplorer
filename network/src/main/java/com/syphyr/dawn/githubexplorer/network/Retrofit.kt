package com.syphyr.dawn.githubexplorer.network

import com.syphyr.dawn.githubexplorer.common.functional.Either
import com.syphyr.dawn.githubexplorer.common.system.Failure
import retrofit2.Call


fun <T> Call<T>.requestBlocking(): Either<Failure, T> {
  return try {
    val response = execute()
    when (response.isSuccessful) {
      true -> Either.Right((response.body()!!))
      false -> Either.Left(Failure.ApiError("No response returned"))
    }
  } catch (exception: Throwable) {
    Either.Left(Failure.ApiError(exception.message ?: "Some Api Error occurred"))
  }
}


