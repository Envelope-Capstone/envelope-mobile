package com.joesamyn.envelope.modules

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

@Module()
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides()
    fun provideEnvelopeDao(envelopeDatabase: EnvelopeDatabase): EnvelopeDao {
        return envelopeDatabase.envelopeDao()
    }

    @Provides
    @Singleton
    fun provideEnvelopeDatabase(@ApplicationContext appContext: Context): EnvelopeDatabase {
        return Room.databaseBuilder(
            appContext,
            EnvelopeDatabase::class.java,
            "envelope_database"
        ).build()
    }

}