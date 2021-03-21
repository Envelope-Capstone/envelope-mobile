package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.EnvelopeRepository
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EnvelopeRepositoryModule {

    @Singleton
    @Provides
    fun provideEnvelopeRepository(envelopeDao: EnvelopeDao): EnvelopeRepository{
        return EnvelopeRepository(envelopeDao)
    }
}