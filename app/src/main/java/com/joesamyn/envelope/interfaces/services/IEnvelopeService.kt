package com.joesamyn.envelope.interfaces.services

import com.joesamyn.envelope.models.Envelope

interface IEnvelopeService {
    fun getEnvelopes(): List<Envelope>
}