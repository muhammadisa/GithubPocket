package com.xoxoer.gitpocket.di.user

import com.xoxoer.gitpocket.network.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object UserModule {
    @Provides
    @JvmStatic
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}