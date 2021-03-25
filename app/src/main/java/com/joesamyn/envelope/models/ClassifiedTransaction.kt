package com.joesamyn.envelope.models

import java.util.*


/**
 * Model to hold classified transaction. A classified transaction is a transaction that has had
 * a category assigned to it from the ML API.
 */
data class ClassifiedTransaction(
        val Id: Int,
        val TransactionDate: Date,
        val PostDate: Date,
        val Description: String,
        val Amount: Double,
        val Envelope: String
)
