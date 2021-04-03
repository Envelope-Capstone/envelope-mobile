package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.models.Transaction
import com.joesamyn.envelope.repositories.interfaces.ClassificationRepository
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class ClassificationRepositoryImpl constructor(
    private val classificationService: ClassificationService,
    private val userRepo: UserRepository,
    private val transactionRepository: TransactionRepository): ClassificationRepository {

    /**
     * Send transaction to ML API to be classified
     */
    override suspend fun classifyTransaction(trx: Transaction): Flow<DataState<ClassifiedTransaction>> = flow {
        emit(DataState.Loading)
        val trxList = listOf(trx)

        try {
            userRepo.getRefreshToken()


            val resp = classificationService.classifyTransaction(userRepo.getAccessToken(), trxList)

            if(resp.isSuccessful) {
                val classifiedTrx = resp.body()!!
                transactionRepository.addTransaction(classifiedTrx)
                emit(DataState.Success(classifiedTrx))
            }
            else {
                emit(DataState.Failed)
            }

        }catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}