package com.xoxoer.gitpocket.di.userdetail

import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.anotation.ViewModelKey
import com.xoxoer.gitpocket.ui.userdetail.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserDetailModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindUserViewModel(userDetailViewModel: UserDetailViewModel): ViewModel
}