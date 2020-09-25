package com.angel.daily_heros.ui.main.qr.camera

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class QRScannerModule {

    @ContributesAndroidInjector
    internal abstract fun contributeQRScannerFragment(): QRScannerFragment

    @Binds
    @IntoMap
    @ViewModelKey(QRScannerViewModel::class)
    abstract fun bindQRScannerViewModel(viewModel: QRScannerViewModel): ViewModel

}
