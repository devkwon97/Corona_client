package com.angel.daily_heros.ui.main.message

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class MessageModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMessageFragment(): MessageFragment

    @Binds
    @IntoMap
    @ViewModelKey(MessageViewModel::class)
    abstract fun bindMessageViewModel(viewModel: MessageViewModel): ViewModel

}
