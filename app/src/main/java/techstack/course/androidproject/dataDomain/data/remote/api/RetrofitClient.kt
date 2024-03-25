package techstack.course.androidproject.dataDomain.data.remote.api

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import techstack.course.androidproject.constants.ServiceConstants
import techstack.course.androidproject.dataDomain.data.remote.api.interceptors.makeLoggingInterceptor
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private val dispatcher = Dispatcher().apply {
        maxRequests = 5
        maxRequestsPerHost = 1
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(makeLoggingInterceptor())
        .dispatcher(dispatcher)
        .build()


    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}