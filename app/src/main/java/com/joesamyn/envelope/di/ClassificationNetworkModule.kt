package com.joesamyn.envelope.di

import com.google.gson.GsonBuilder
import com.joesamyn.envelope.repositories.AuthenticationService
import com.joesamyn.envelope.repositories.ClassificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassificationNetworkModule {

    @Singleton
    @Provides
    fun provideClassificationService(): ClassificationService {
        return Retrofit.Builder()
            .baseUrl("https://envelopemlapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ClassificationService::class.java)
    }

}