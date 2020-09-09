package com.xoxoer.gitpocket.di

import com.xoxoer.gitpocket.di.popularity.PopularityModule
import com.xoxoer.gitpocket.di.popularity.PopularityViewModelModule
import com.xoxoer.gitpocket.di.user.UserModule
import com.xoxoer.gitpocket.di.user.UserViewModelModule
import com.xoxoer.gitpocket.di.userdetail.UserDetailModelModule
import com.xoxoer.gitpocket.di.userdetail.UserDetailModule
import com.xoxoer.gitpocket.ui.popularity.PopularityActivity
import com.xoxoer.gitpocket.ui.splash.SplashActivity
import com.xoxoer.gitpocket.ui.user.UserActivity
import com.xoxoer.gitpocket.ui.userdetail.UserDetailActivity
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

    @ContributesAndroidInjector(
        modules = [
            UserDetailModelModule::class,
            UserDetailModule::class
        ]
    )
    abstract fun contributeUserDetailActivity(): UserDetailActivity

    @ContributesAndroidInjector(
        modules = [
            PopularityViewModelModule::class,
            PopularityModule::class
        ]
    )
    abstract fun contributePopularityActivity(): PopularityActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

}