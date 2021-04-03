package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.*
import com.joesamyn.envelope.repositories.interfaces.ClassificationRepository
import com.joesamyn.envelope.repositories.mappers.TransactionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassificationRepositoryModule {

    @Singleton
    @Provides
    fun provideClassificationRepository(classificationService: ClassificationService,
                                        userRepository: UserRepository,
                                        transactionRepository: TransactionRepository,
                                        envelopeRepository: EnvelopeRepository,
                                        transactionMapper: TransactionMapper): ClassificationRepository {
        return ClassificationRepositoryImpl(classificationService, userRepository,
                transactionRepository, envelopeRepository, transactionMapper)
    }
}