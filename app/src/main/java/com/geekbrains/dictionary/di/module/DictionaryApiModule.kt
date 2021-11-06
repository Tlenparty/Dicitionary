package com.geekbrains.dictionary.di.module

import com.geekbrains.dictionary.api.DictionaryApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DictionaryApiModule {
    companion object {
        private const val API_URL_KEY = "api_url"
        private const val API_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }

    @Named(API_URL_KEY)
    @Provides
    fun provideBaseUrl(): String = API_URL

    @Singleton
    @Provides
    fun provideGithubApi(@Named(API_URL_KEY) baseUrl: String): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
}
