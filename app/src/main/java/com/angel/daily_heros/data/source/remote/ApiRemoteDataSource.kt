package com.angel.weiserqrassetmanagerforsekr.data.source.remote


import com.angel.daily_heros.data.source.ApiDataSource
import com.angel.daily_heros.data.source.remote.service.ApiService
import javax.inject.Inject


class ApiRemoteDataSource @Inject constructor(
    private val api: ApiService
) : ApiDataSource {
//    override suspend fun getManagingReleaseAssets(request: ManagingAssetsRequest): List<ManagingAssetModel> =
//        api.getManagingReleaseAssets(request)



}