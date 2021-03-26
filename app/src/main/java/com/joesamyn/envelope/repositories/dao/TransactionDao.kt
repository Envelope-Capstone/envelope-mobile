package com.joesamyn.envelope.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joesamyn.envelope.repositories.entities.ClassifiedTransactionEntity

@Dao
interface TransactionDao {

    /**
     * Get all transactions for a specific envelope
     */
    @Query("SELECT * FROM transactions WHERE Envelope = :envelopeName")
    suspend fun getTransactionsForEnvelope(envelopeName: String): List<ClassifiedTransactionEntity>

    /**
     * Add transaction to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transaction: ClassifiedTransactionEntity)
}