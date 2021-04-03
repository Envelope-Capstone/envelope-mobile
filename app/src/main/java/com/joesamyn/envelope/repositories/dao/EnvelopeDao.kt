package com.joesamyn.envelope.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity

/**
 * The DAO is responsible for mapping SQLite queries to method calls. Must be an abstract class
 * or interface
 */

@Dao
interface EnvelopeDao {

    /**
     * Get all envelopes from SQLite table
     */
    @Query("SELECT * FROM envelopes")
    suspend fun getAllEnvelopes(): List<EnvelopeEntity>

    /**
     * Insert new envelope into SQLite table
     * (Replace entry on conflict)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(envelope: EnvelopeEntity)

    @Query("UPDATE envelopes SET total = total + :cost WHERE name = :envelope")
    suspend fun updateEnvelopeTotal(cost: Double, envelope: String)
}