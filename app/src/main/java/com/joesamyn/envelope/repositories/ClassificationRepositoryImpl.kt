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
    private val userRepo: UserRepository): ClassificationRepository {

    /**
     * Send transaction to ML API to be classified
     */
    override suspend fun classifyTransaction(trx: Transaction): Flow<DataState<ClassifiedTransaction>> = flow {
        emit(DataState.Loading)
        val trxList = listOf(trx)

        try {
            // TODO: Setup getAccessToken method to refresh if token is expired
            val resp = classificationService.classifyTransaction(userRepo.getAccessToken(), trxList)

            if(resp.isSuccessful) {
                emit(DataState.Success(resp.body()!!))
            }

        }catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}