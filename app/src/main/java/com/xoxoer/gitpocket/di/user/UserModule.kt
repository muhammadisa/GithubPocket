package com.xoxoer.gitpocket.di.user

import com.xoxoer.gitpocket.network.UserApi
import com.xoxoer.gitpocket.ui.user.UserViewModel
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object UserModule {

    @Provides
    @JvmStatic
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}