package ru.practicum.android.diploma.global.di

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.global.data.network.HhApi
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.RetrofitNetworkClient
import ru.practicum.android.diploma.global.util.NetworkUtil
import java.util.concurrent.TimeUnit

val dataModule = module {
    single<HhApi> {
        val client = OkHttpClient.Builder()
            .callTimeout(NetworkUtil.CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkUtil.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.HH_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HhApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(
            hhApi = get(),
            networkUtil = NetworkUtil(context = androidContext())
        )
    }
}
