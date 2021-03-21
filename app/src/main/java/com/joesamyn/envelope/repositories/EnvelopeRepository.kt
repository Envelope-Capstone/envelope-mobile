package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.entities.EnvelopeEntity
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** NOTES */

class EnvelopeRepository
constructor(private val envelopeDao: EnvelopeDao){

    suspend fun getEnvelopes(): Flow<DataState<List<EnvelopeEntity>>> = flow {
        emit(DataState.Loading)
        delay(1000) // TODO: Remove when done testing
        try {
            // Fetch envelopes from table
            val envelopes = envelopeDao.getAllEnvelopes()

            // Emit the retrieved events to the UI
            emit(DataState.Success(envelopes))
        }catch(e: Exception){
            // If exception emit an exception
            emit(DataState.Error(e))
        }
    }
}