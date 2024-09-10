package ru.practicum.android.diploma.global.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.favorites.data.FavoriteRepositoryImpl
import ru.practicum.android.diploma.favorites.data.VacancyDbConvertor
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.global.data.network.HhApi
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.RetrofitNetworkClient
import ru.practicum.android.diploma.global.db.AppDatabase
import ru.practicum.android.diploma.global.util.HeaderInterceptor
import ru.practicum.android.diploma.global.util.NetworkUtil
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper
import ru.practicum.android.diploma.search.data.repository.SearchRepositoryImpl
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.sharing.data.ExternalNavigatorImpl
import ru.practicum.android.diploma.sharing.domain.api.ExternalNavigator
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

    factory<SearchRepository> {
        SearchRepositoryImpl(networkClient = get(), vacancyMapper = get(), database = get())
    }

    factory {
        VacancyMapper()
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db").build()
    }

    factory {
        VacancyDbConvertor()
    }

    factory<FavoriteRepository> {
        FavoriteRepositoryImpl(get(), get())
    }

    factory<ExternalNavigator> {
        ExternalNavigatorImpl(application = get())
    }
}
