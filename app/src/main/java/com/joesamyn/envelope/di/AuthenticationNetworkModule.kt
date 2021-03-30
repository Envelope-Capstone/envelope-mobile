package com.joesamyn.envelope.di

import androidx.core.content.res.TypedArrayUtils.getString
import com.google.gson.GsonBuilder
import com.joesamyn.envelope.R
import com.joesamyn.envelope.repositories.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationNetworkModule {

    @Singleton
    @Provides
    fun provideAuthenticationService(): AuthenticationService{
        return Retrofit.Builder()
            .baseUrl("https://envelopeauthapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AuthenticationService::class.java)
    }
}