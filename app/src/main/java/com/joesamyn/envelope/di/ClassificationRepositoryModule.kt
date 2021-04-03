package com.joesamyn.envelope.di

import com.joesamyn.envelope.repositories.ClassificationRepositoryImpl
import com.joesamyn.envelope.repositories.ClassificationService
import com.joesamyn.envelope.repositories.TransactionRepository
import com.joesamyn.envelope.repositories.UserRepository
import com.joesamyn.envelope.repositories.interfaces.ClassificationRepository
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
                                        transactionRepository: TransactionRepository): ClassificationRepository {
        return ClassificationRepositoryImpl(classificationService, userRepository, transactionRepository)
    }
}