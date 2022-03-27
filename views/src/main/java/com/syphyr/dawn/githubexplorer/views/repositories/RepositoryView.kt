package com.syphyr.dawn.githubexplorer.views.repositories

import com.syphyr.dawn.githubexplorer.views.Data

data class RepositoryView(
  val id: Long,
  val name: String,
  val imageUrl: String = "",
  val description: String,
  val language: String,
  val forksCount: Int,
  val issuesCount: Int,
  val starredBy: Int,
  val lastReleaseVersion: Double
) : Data