package com.devfinity.composeboilerplate.di

import com.devfinity.composeboilerplate.BuildConfig
import com.devfinity.composeboilerplate.persistance.prefs.SharedPrefManager
import com.devfinity.composeboilerplate.remote.ApiService
import com.devfinity.composeboilerplate.remote.NetworkInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setLenient().create()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(networkInterceptor)
                addInterceptor(httpLoggingInterceptor)
                connectTimeout(40, TimeUnit.SECONDS)
                readTimeout(40, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient,
        gson: Gson,
        sharedPreferenceManager: SharedPrefManager
    ): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
//            .baseUrl(Environment.getServerUrl(sharedPreferenceManager))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}