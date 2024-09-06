package ru.practicum.android.diploma.global.di

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.global.data.network.HhApi
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.RetrofitNetworkClient
import ru.practicum.android.diploma.global.util.HeaderInterceptor
import ru.practicum.android.diploma.global.util.NetworkUtil
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.impl.SearchInteractorImpl
import ru.practicum.android.diploma.vacancy.ui.viewmodel.DetailsVacancyViewModel
import java.util.concurrent.TimeUnit

val dataModule = module {
    single<HhApi> {
        val client = OkHttpClient.Builder()
            .callTimeout(NetworkUtil.CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkUtil.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
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
    // потом во вьюмодель перенесем
    factory<SearchInteractor> {
        SearchInteractorImpl()
    }
    viewModel<DetailsVacancyViewModel> {
        DetailsVacancyViewModel(get())
    }
}
