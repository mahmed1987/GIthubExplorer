package com.syphyr.dawn.githubexplorer.repositories.di

import com.syphyr.dawn.githubexplorer.repositories.github.GithubWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

  @Provides
  @Singleton
  fun provideGithubWebServices(retrofit: Retrofit) = retrofit.create(GithubWebServices::class.java)
}

