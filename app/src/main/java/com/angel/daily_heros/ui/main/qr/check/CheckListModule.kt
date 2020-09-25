package com.angel.daily_heros.ui.main.qr.check

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class CheckListModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCheckListFragment(): CheckListFragment

    @Binds
    @IntoMap
    @ViewModelKey(CheckListViewModel::class)
    abstract fun bindCheckListViewModel(viewModel: CheckListViewModel): ViewModel

}
