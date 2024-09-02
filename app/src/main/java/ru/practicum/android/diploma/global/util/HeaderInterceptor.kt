package ru.practicum.android.diploma.global.util

import okhttp3.Interceptor
import okhttp3.Response
import ru.practicum.android.diploma.BuildConfig

class HeaderInterceptor : Interceptor {

    @Override
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .addHeader("Authorization: Bearer", BEARER_TOKEN)
            .addHeader("HH-User-Agent", USER_AGENT_STRING)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val USER_AGENT_STRING = "Offers (gabov@list.ru)"
        private const val BEARER_TOKEN = BuildConfig.HH_ACCESS_TOKEN
    }
}
