package com.joesamyn.envelope.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joesamyn.envelope.entities.EnvelopeEntity
import kotlinx.coroutines.flow.Flow

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
    fun getAllEnvelopes(): Flow<List<EnvelopeEntity>> // Flow allows us to observe changes in the data stored in this table

    /**
     * Insert new envelope into SQLite table
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(envelope: EnvelopeEntity)
}