package com.syphyr.dawn.githubexplorer.repositories.github

import com.syphyr.dawn.githubexplorer.views.remoteObjects.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubWebServices {
  @GET("search/repositories")
  fun repositories(@Query("q") query: String) : Call<RepositoryResponse>
}