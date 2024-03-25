package techstack.course.androidproject.dataDomain.data.remote.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import techstack.course.androidproject.BuildConfig

internal fun makeLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return logging
}
