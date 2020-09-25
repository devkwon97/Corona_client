package com.angel.daily_heros.ui.main.history

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
@Suppress("UNUSED")
internal abstract class HistoryModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHistoryFragment(): HistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

}
