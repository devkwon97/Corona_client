package com.angel.daily_heros.data.source.remote


import com.angel.daily_heros.BuildConfig
import com.angel.daily_heros.data.source.remote.service.ApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class ApiFactory {
    val TIMEOUT_SEC = 30L

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        var request = chain.request()
        val url = request.url.newBuilder().build()
        request = request
            .newBuilder()
            .url(url)
//            .addHeader(HEADER_AUTHORIZATION_KEY, "Bearer ${UserLocalDataSource.accessToken}")
//            .addHeader(HEADER_OS_TYPE_KEY, HEADER_OS_TYPE_SPEAKER)
//            .addHeader(HEADER_OS_VERSION_KEY, "1.1.1") // TODO Dummy
//            .addHeader(HEADER_APP_VERSION_KEY, BuildConfig.VERSION_NAME)
            .build()
        chain.proceed(request)
    }

    private val logInterceptor: Interceptor
        get() = HttpLoggingInterceptor()
            .apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }


    /**
     * OkhttpClient for building http request url
     */
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logInterceptor)
        .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

    private val gson = GsonBuilder()
        // this setting make pascal case key into camel case key for api
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val serviceApi: ApiService = retrofit().create(ApiService::class.java)


}