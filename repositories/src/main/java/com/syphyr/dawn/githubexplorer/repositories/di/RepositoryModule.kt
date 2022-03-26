package com.syphyr.dawn.githubexplorer.repositories.di

import com.syphyr.dawn.githubexplorer.repositories.github.GithubDataSource
import com.syphyr.dawn.githubexplorer.repositories.github.GithubRepository
import com.syphyr.dawn.githubexplorer.repositories.github.GithubWebServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGithubWebServices(retrofit: Retrofit) = retrofit.create(GithubWebServices::class.java)

  @InstallIn(SingletonComponent::class)
  @Module
  interface BindsModule {
    @Binds
    fun bindGithubDataSource(githubRepository: GithubRepository): GithubDataSource
  }
}

