package com.xoxoer.gitpocket.di.popularity

import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.di.ViewModelKey
import com.xoxoer.gitpocket.ui.popularity.PopularityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PopularityViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PopularityViewModel::class)
    abstract fun bindPopularityViewModel(popularityViewModel: PopularityViewModel): ViewModel
}