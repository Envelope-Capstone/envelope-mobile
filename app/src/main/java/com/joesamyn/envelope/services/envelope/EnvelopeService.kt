package com.joesamyn.envelope.services.envelope

import com.joesamyn.envelope.interfaces.repositories.IEnvelopeRepository
import com.joesamyn.envelope.interfaces.services.IEnvelopeService
import com.joesamyn.envelope.models.Envelope
import javax.inject.Inject

class EnvelopeService @Inject constructor(): IEnvelopeService {

    /**
     * Private DI Variables
     */
    @Inject
    private lateinit var envelopRepository: IEnvelopeRepository

    /**
     * Gets all envelope categories
     */
    override fun getEnvelopes(): List<Envelope> {
        return envelopRepository.getEnvelopes()
    }
}