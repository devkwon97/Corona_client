package com.angel.daily_heros.data.repository


import com.angel.daily_heros.data.source.ApiDataSource
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    @Named("remoteApiSource") private val apiRemoteDataSource: ApiDataSource

) {
//    suspend fun getCompanyAddrList(request: UserInfoRequest): List<CompanyInfoResponse> {
//        return try {
//            val res = apiRemoteDataSource.getCompanyAddrList(request)
//            return res
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            throw IllegalStateException("Fail getCompanyAddrList request with ${request}")
//        }
//    }

}