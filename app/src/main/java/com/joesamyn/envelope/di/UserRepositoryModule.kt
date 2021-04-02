package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    /**
     * Provide instance of user repository for DI
     */
    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }
}