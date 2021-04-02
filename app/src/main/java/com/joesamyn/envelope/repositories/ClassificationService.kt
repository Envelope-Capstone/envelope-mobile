package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.models.Transaction
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClassificationService {

    @POST("classify_trx")
    suspend fun classifyTransaction(@Header("Authorization") token: String, @Body trx: Transaction): Response<ClassifiedTransaction>
}