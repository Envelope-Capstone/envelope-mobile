package com.joesamyn.envelope.modules

import android.app.Application
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
import com.joesamyn.envelope.services.envelope.EnvelopeService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

@InstallIn(Application::class)
@Module
abstract class EnvelopeServiceModule {

    @Binds
    @Singleton
    abstract fun envelopeService(service: EnvelopeService): IEnvelopeService
}