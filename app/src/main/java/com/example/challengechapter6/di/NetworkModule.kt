package com.example.challengechapter6.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object{
        const val BASE_URL = "https://easy-school-uniform-ant.cyclic.app/api/v1/"
        const val BASE_URL_MAIN = "https://www.freetogame.com/api/"
        const val RETROFIT_AUTH = "RetrofitAuth"
        const val RETROFIT_MAIN = "RetrofitMain"
    }

    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    @Named(RETROFIT_AUTH)
    fun provideRetrofitAuth(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    @Named(RETROFIT_MAIN)
    fun provideRetrofitMain(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthAPI(@Named(RETROFIT_AUTH) retrofit: Retrofit) : com.example.data.remote.service.AuthAPI {
        return retrofit.create(com.example.data.remote.service.AuthAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMainAPI(@Named(RETROFIT_MAIN) retrofit: Retrofit) : com.example.data.remote.service.MainAPI {
        return retrofit.create(com.example.data.remote.service.MainAPI::class.java)
    }
}