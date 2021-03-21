package com.joesamyn.envelope.di

import android.content.Context
import androidx.room.Room
import com.joesamyn.envelope.repositories.dao.EnvelopeDao
import com.joesamyn.envelope.repositories.database.EnvelopeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /**
     * Provide instance to Envelope Database class
     */
    @Singleton
    @Provides
    fun provideEnvelopeDb(@ApplicationContext context: Context): EnvelopeDatabase {
        return Room.databaseBuilder(
            context,
            EnvelopeDatabase::class.java,
            EnvelopeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provide instance of Envelope DAO
     */
    @Singleton
    @Provides
    fun provideEnvelopeDao(envelopeDatabase: EnvelopeDatabase): EnvelopeDao {
        return envelopeDatabase.envelopeDao()
    }
}