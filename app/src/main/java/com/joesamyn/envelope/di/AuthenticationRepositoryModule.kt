package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.AuthenticationRepositoryImpl
import com.joesamyn.envelope.repositories.AuthenticationService
import com.joesamyn.envelope.repositories.interfaces.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationRepositoryModule {

    /**
     * Provide instance of the AuthenticationRepository
     */
    @Singleton
    @Provides
    fun provideAuthenticationRepository(authService: AuthenticationService): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authService)
    }

}