package com.xoxoer.gitpocket.di.repo

import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.di.ViewModelKey
import com.xoxoer.gitpocket.ui.repo.RepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepoViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel
}