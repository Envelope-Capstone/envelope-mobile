package com.joesamyn.envelope.di

import com.google.gson.GsonBuilder
import com.joesamyn.envelope.repositories.ClassificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ClassificationNetworkModule {

    @Singleton
    @Provides
    fun provideClassificationService(): ClassificationService {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        return Retrofit.Builder()
            .baseUrl("https://envelopemlapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create()))
            .client(client)
            .build()
            .create(ClassificationService::class.java)
    }

}