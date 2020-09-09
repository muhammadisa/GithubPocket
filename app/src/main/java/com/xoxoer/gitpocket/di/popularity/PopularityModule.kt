package com.xoxoer.gitpocket.di.popularity

import com.xoxoer.gitpocket.network.PopularityApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PopularityModule {
    @Provides
    @JvmStatic
    fun providePopularityApi(retrofit: Retrofit): PopularityApi {
        return retrofit.create(PopularityApi::class.java)
    }
}