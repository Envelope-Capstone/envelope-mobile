package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import com.joesamyn.envelope.repositories.mappers.EnvelopeMapper
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EnvelopeRepository
constructor(private val envelopeDao: EnvelopeDao, private val envelopeMapper: EnvelopeMapper){

    suspend fun getEnvelopes(): Flow<DataState<List<Envelope>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            // Fetch envelopes from table
            val envelopesEntity = envelopeDao.getAllEnvelopes()

            // Map to proper domain model
            val envelopes = envelopeMapper.mapFromEntityList(envelopesEntity)

            // Emit the retrieved events to the UI
            emit(DataState.Success(envelopes))
        }catch(e: Exception){
            // If exception emit an exception
            emit(DataState.Error(e))
        }
    }

    suspend fun addEnvelope(envelope: Envelope) {
        val envelopeEntity = envelopeMapper.mapToEntity(envelope)
        envelopeDao.insert(envelopeEntity)
    }
}