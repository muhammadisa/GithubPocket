package com.xoxoer.gitpocket.di.repo

import com.xoxoer.gitpocket.network.RepoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object RepoModule {
    @Provides
    @JvmStatic
    fun provideRepoApi(retrofit: Retrofit): RepoApi {
        return retrofit.create(RepoApi::class.java)
    }
}