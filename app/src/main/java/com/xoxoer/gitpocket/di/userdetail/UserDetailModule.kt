package com.xoxoer.gitpocket.di.userdetail

import com.xoxoer.gitpocket.network.UserDetailApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object UserDetailModule {
    @Provides
    @JvmStatic
    fun provideUserDetailApi(retrofit: Retrofit): UserDetailApi {
        return retrofit.create(UserDetailApi::class.java)
    }
}