package com.joesamyn.envelope.repositories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joesamyn.envelope.entities.EnvelopeEntity
import com.joesamyn.envelope.repositories.dao.EnvelopeDao

@Database(entities = [EnvelopeEntity::class], version = 1, exportSchema = false)
public abstract class EnvelopeDatabase : RoomDatabase(){

    abstract fun envelopeDao(): EnvelopeDao

    companion object {

        // Keep object a singleton to prevent multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: EnvelopeDatabase? = null


        fun getDatabase(context: Context): EnvelopeDatabase {
            // If instance is not null then return it
            // If it is null then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EnvelopeDatabase::class.java,
                    "envelope_database"
                ).build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}