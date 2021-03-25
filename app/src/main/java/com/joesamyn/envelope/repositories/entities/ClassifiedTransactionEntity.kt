package com.joesamyn.envelope.repositories.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.joesamyn.envelope.models.Envelope
import java.util.*

@Entity(tableName = "transactions")
data class ClassifiedTransactionEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "TransactionDate") var TransactionDate: Date,
        @ColumnInfo(name = "PostDate") var PostDate: Date,
        @ColumnInfo(name = "Description") var Description: String,
        @ColumnInfo(name = "Amount") var Amount: Double,
        @ColumnInfo(name = "Envelope") var Envelope: String
)
