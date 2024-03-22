package techstack.course.androidproject.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import techstack.course.androidproject.BuildConfig
import techstack.course.androidproject.constants.ServiceConstants
import techstack.course.androidproject.data.responses.FactResponse
import java.util.concurrent.TimeUnit


internal fun makeLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return logging
}

object RetrofitService {
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

interface FactService {

    @GET("data")
    suspend fun getFact(
        @Query("drilldowns") drillDowns: String,
        @Query("measures") measures: String
    ): FactResponse //Call<FactResponse>

}

fun main(): Unit = runBlocking {
    val factService = RetrofitService.createService(FactService::class.java)

    val response = factService.getFact("Nation", "Population")

    println(response.data?.get(0)?.nation)

    delay(10000)
}