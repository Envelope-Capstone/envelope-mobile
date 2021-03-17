package com.joesamyn.envelope.interfaces.repositories

import com.joesamyn.envelope.models.Envelope

interface IEnvelopeRepository {

    fun getEnvelopes(): List<Envelope>
}