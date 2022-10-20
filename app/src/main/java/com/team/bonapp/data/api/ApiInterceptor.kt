package com.team.bonapp.data.api

import com.team.bonapp.BuildConfig
import okhttp3.Interceptor

object ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
            chain.request().url.newBuilder().addQueryParameter("app_id", BuildConfig.app_id)
                .addQueryParameter("app_key", BuildConfig.app_key).build()
        ).build()
    )
}