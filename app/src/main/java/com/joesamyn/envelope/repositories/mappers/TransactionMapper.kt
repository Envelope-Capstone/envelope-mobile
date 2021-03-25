package com.joesamyn.envelope.repositories.mappers

import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.entities.ClassifiedTransactionEntity
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.util.IEntityDomainMapper
import javax.inject.Inject

class TransactionMapper @Inject constructor(): IEntityDomainMapper<ClassifiedTransactionEntity, ClassifiedTransaction> {

    /**
     * Map ClassifiedTransactionEntity to ClassifiedTransaction domain model
     */
    override fun mapFromEntity(entity: ClassifiedTransactionEntity): ClassifiedTransaction {
        return ClassifiedTransaction(
                Id = entity.id,
                TransactionDate = entity.TransactionDate,
                PostDate = entity.PostDate,
                Envelope = entity.Envelope,
                Description = entity.Description,
                Amount = entity.Amount
        )
    }

    /**
     * Map ClassifiedTransaction domain model to ClassifiedTransactionEntity
     */
    override fun mapToEntity(domain: ClassifiedTransaction): ClassifiedTransactionEntity {
        return ClassifiedTransactionEntity(
                id = domain.Id,
                TransactionDate = domain.TransactionDate,
                PostDate = domain.PostDate,
                Envelope = domain.Envelope,
                Description = domain.Description,
                Amount = domain.Amount
        )
    }

    /**
     * Map from a list of entities to a list of domain models
     */
    fun mapFromEntityList(transactions: List<ClassifiedTransactionEntity>): List<ClassifiedTransaction>{
        return transactions.map { mapFromEntity(it) }
    }
}