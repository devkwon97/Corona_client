package com.angel.daily_heros.di

import com.angel.daily_heros.data.repository.ApiRepository
import com.angel.daily_heros.data.source.ApiDataSource
import com.angel.weiserqrassetmanagerforsekr.data.source.remote.ApiRemoteDataSource
import com.angel.daily_heros.data.source.remote.service.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Module where classes created in the shared module are created.
 */
@Module
class RepositoryModule {

// Define the data source implementations that should be used. All data sources are singletons.

    @Singleton
    @Provides
    fun provideApiRepository(
        @Named("remoteApiSource") apiRemoteDataSource: ApiDataSource
    ): ApiRepository {
        return ApiRepository(apiRemoteDataSource)
    }

    @Singleton
    @Provides
    @Named("remoteApiSource")
    fun provideApiRemoteDataSource(api: ApiService): ApiDataSource {
        return ApiRemoteDataSource(api)
    }


}