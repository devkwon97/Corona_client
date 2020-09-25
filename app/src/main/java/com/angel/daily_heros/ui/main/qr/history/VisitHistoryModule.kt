package com.angel.daily_heros.ui.main.qr.history

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class VisitHistoryModule {

    @ContributesAndroidInjector
    internal abstract fun contributeVisitHistoryFragment(): VisitHistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(VisitHistoryViewModel::class)
    abstract fun bindVisitHistoryViewModel(viewModel: VisitHistoryViewModel): ViewModel

}
