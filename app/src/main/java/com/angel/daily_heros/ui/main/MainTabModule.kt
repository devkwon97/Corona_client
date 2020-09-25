package com.angel.daily_heros.ui.main

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
@Suppress("UNUSED")
internal abstract class MainTabModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainTabsFragment(): MainTabsFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainTabsViewModel::class)
    abstract fun bindMainSplashViewModel(viewModel: MainTabsViewModel): ViewModel

}

