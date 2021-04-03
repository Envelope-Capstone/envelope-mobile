package com.joesamyn.envelope.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ClassifiedTransactionResp(
        @SerializedName("TransactionDate")
        val TransactionDate: Date,
        @SerializedName("PostDate")
        val PostDate: Date,
        @SerializedName("Description")
        val Description: String,
        @SerializedName("Amount")
        val Amount: Double,
        @SerializedName("Category")
        val Envelope: String
)
