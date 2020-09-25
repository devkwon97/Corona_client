package com.angel.daily_heros.di

import com.angel.daily_heros.data.source.remote.ApiFactory
import com.angel.daily_heros.data.source.remote.service.ApiService
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
class ApiModule {

    @Provides
    @Singleton
    fun provideApiFactory(): ApiFactory {
        return ApiFactory()
    }

    @Provides
    @Singleton
    fun provideServiceApi(factory: ApiFactory): ApiService {
        return factory.serviceApi
    }


}
