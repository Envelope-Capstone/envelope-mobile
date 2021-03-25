package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.TransactionRepository
import com.joesamyn.envelope.repositories.dao.TransactionDao
import com.joesamyn.envelope.repositories.mappers.TransactionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionRepositoryModule {

    @Singleton
    @Provides
    fun provideTransactionRepository(
            transactionDao: TransactionDao,
            transactionMapper: TransactionMapper):
            TransactionRepository {
        return TransactionRepository(transactionDao, transactionMapper)
    }
}