package com.joesamyn.envelope.repositories.database

import android.content.Context
import androidx.room.*
import com.joesamyn.envelope.repositories.DateConverter
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import com.joesamyn.envelope.repositories.dao.TransactionDao
import com.joesamyn.envelope.repositories.entities.ClassifiedTransactionEntity

@Database(entities = [EnvelopeEntity::class, ClassifiedTransactionEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
public abstract class EnvelopeDatabase : RoomDatabase(){

    abstract fun envelopeDao(): EnvelopeDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        val DATABASE_NAME: String = "envelope_db"
    }
}