package com.nextup.bulkybookapp.di

import android.app.Application
import android.app.Service
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.domain.network.NetworkConnection
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
object Module {


    @Singleton
    @Provides
    fun provideService(dataStore: DataStore): Service {
        val baseUrl = "https://localhost:7128/"

        val token = dataStore.readString(dataStore.tags().TOKEN_TAG)

        val logging = HttpLoggingInterceptor()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttp =  OkHttpClient.Builder().addInterceptor(logging).connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request =
                        chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                    return chain.proceed(request)
                }
            }).readTimeout(60, TimeUnit.SECONDS).build()

        val serviceBuilder = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp).build()

        return serviceBuilder.create(Service::class.java)
    }


    @Provides
    @Singleton
    fun provideDataStore(app: Application): DataStore {
        return DataStore(app)
    }

    @Provides
    @Singleton
    fun provideNetworkConnection(app: Application): NetworkConnection {
        return NetworkConnection(app)
    }





}