package com.joesamyn.envelope.repositories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.repositories.dao.EnvelopeDao

@Database(entities = [EnvelopeEntity::class], version = 2, exportSchema = false)
public abstract class EnvelopeDatabase : RoomDatabase(){

    abstract fun envelopeDao(): EnvelopeDao

    companion object {
        val DATABASE_NAME: String = "envelope_db"
    }
}