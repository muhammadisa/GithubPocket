package com.xoxoer.gitpocket.di.user

import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.anotation.ViewModelKey
import com.xoxoer.gitpocket.ui.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel
}