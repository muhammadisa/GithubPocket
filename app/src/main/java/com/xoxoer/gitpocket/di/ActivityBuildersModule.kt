package com.xoxoer.gitpocket.di

import com.xoxoer.gitpocket.di.user.UserModule
import com.xoxoer.gitpocket.di.user.UserViewModelModule
import com.xoxoer.gitpocket.ui.splash.SplashActivity
import com.xoxoer.gitpocket.ui.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            UserViewModelModule::class,
            UserModule::class
        ]
    )
    abstract fun contributeUserActivity(): UserActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

}