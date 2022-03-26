package com.syphyr.dawn.githubexplorer.repositories.github

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubWebServices {
  @GET("search/repositories")
  fun repositories(@Query("q") query: String)
}