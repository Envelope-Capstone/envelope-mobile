package com.joesamyn.envelope.repositories.interfaces

import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.models.ClassifiedTransactionResp
import com.joesamyn.envelope.models.Transaction
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.flow.Flow

interface ClassificationRepository {

    /**
     * send transaction to ML API to be classified
     */
    suspend fun classifyTransaction(trx: Transaction): Flow<DataState<ClassifiedTransaction>>
}