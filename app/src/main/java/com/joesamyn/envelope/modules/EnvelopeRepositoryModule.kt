package com.joesamyn.envelope.modules

import android.app.Application
import com.joesamyn.envelope.interfaces.repositories.IEnvelopeRepository
import com.joesamyn.envelope.repositories.envelope.EnvelopeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class EnvelopeRepositoryModule {

    @Binds
    @Singleton
    abstract fun envelopeRepository(repository: EnvelopeRepository): IEnvelopeRepository
}