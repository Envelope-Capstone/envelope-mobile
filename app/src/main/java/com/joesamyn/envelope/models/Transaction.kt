package com.joesamyn.envelope.models

import java.sql.Date

/**
 * Model for unclassified transaction
 */
data class Transaction(
        val TransactionDate: Date,
        val PostDate: Date,
        val Description: String,
        val Amount: Double
)