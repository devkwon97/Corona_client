package com.angel.daily_heros.di

import android.content.Context
import com.angel.daily_heros.MainApplication
import com.angel.daily_heros.data.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesSharedPreferenceStorage(context: Context): SharedPreferenceStorage =
        SharedPreferenceStorage(context)

}
