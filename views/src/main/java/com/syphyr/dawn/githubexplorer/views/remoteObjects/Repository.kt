package com.syphyr.dawn.githubexplorer.views.remoteObjects

import com.google.gson.annotations.SerializedName
import com.syphyr.dawn.githubexplorer.views.repositories.RepositoryView

class RepositoryResponse(
  @SerializedName("total_count") val totalCount: Int,
  @SerializedName("items") val repositories: List<Repository>
)

class Repository(
  val id: Long,
  @SerializedName("full_name") val name: String,
  val description: String?,
  val owner: Owner,
  @SerializedName("stargazers_count") val stargazersCount: Int,
  @SerializedName("forks") val forksCount: Int,
  val language: String?,
  @SerializedName("open_issues") val issueCount: Int
) {
  fun toView() = RepositoryView(
    id = id,
    name = name,
    imageUrl = owner.imageUrl,
    language = language ?: "Not Available",
    description = description?:"Not Available",
    starredBy = stargazersCount,
    forksCount = forksCount,
    issuesCount = issueCount,
    lastReleaseVersion = 0.0
  )
}

class Owner(@SerializedName("avatar_url") val imageUrl: String)