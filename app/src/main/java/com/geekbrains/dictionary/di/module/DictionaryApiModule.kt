package com.geekbrains.dictionary.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.geekbrains.dictionary.api.DictionaryApi
import com.geekbrains.dictionary.helpers.consts.Api
import com.geekbrains.core_helpers.consts.Scopes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DictionaryApiModule {
    fun create() = module {
        single<OkHttpClient>(qualifier = named(Scopes.OK_HTTP_CLIENT)) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

        single<CallAdapter.Factory>(qualifier = named(Scopes.CALLBACK)) {
            CoroutineCallAdapterFactory()
        }

        single<Converter.Factory>(qualifier = named(Scopes.CONVERTER)) {
            GsonConverterFactory.create()
        }

        single<DictionaryApi>(qualifier = named(Scopes.API_MODULE)) {
            Retrofit.Builder()
                .baseUrl(Api.API_URL)
                .client(get(qualifier = named(Scopes.OK_HTTP_CLIENT)))
                .addCallAdapterFactory(get(qualifier = named(Scopes.CALLBACK)))
                .addConverterFactory(get(qualifier = named(Scopes.CONVERTER)))
                .build()
                .create(DictionaryApi::class.java)
        }
    }
}