package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.repositories.dao.TransactionDao
import com.joesamyn.envelope.repositories.mappers.TransactionMapper
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepository
constructor(private val transactionDao: TransactionDao, private val mapper: TransactionMapper){

    /**
     * Get all transactions from the transaction table that belong to a specific envelope
     */
    suspend fun getTransactionsForEnvelope(envelope: String): Flow<DataState<List<ClassifiedTransaction>>> = flow {
        emit(DataState.Loading)
        try {
            // Fetch transactions from table for envelope
            val transactionsEntity = transactionDao.getTransactionsForEnvelope(envelope)

            // Map to domain model
            val transactions = mapper.mapFromEntityList(transactionsEntity)
            emit(DataState.Success(transactions))
        } catch(ex: Exception){
            emit(DataState.Error(ex))
        }
    }

    /**
     * Add transaction to the transactions SQLite table
     */
    suspend fun addTransaction(transaction: ClassifiedTransaction){
        val transactionEntity = mapper.mapToEntity(transaction)
        transactionDao.addTransaction(transactionEntity)
    }

    suspend fun getEnvelopeTransactions(envelope: String): List<ClassifiedTransaction>{
        val transactionsEntity = transactionDao.getTransactionsForEnvelope(envelope)
        // Map to domain model
        return mapper.mapFromEntityList(transactionsEntity)
    }
}