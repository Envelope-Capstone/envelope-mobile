package com.joesamyn.envelope.modules

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
import com.joesamyn.envelope.services.envelope.EnvelopeService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class EnvelopeServiceModule {

    @Binds
    abstract fun envelopeService(service: EnvelopeService): IEnvelopeService
}