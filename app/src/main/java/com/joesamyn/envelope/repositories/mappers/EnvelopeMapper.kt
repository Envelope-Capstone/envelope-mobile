package com.joesamyn.envelope.repositories.mappers

import com.joesamyn.envelope.R
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.util.IEntityDomainMapper
import javax.inject.Inject

class EnvelopeMapper @Inject constructor(): IEntityDomainMapper<EnvelopeEntity, Envelope> {

    override fun mapFromEntity(entity: EnvelopeEntity): Envelope {
        return Envelope(
            id = entity.id,
            name = entity.name,
            icon = getIcon(entity.icon),
            total = entity.total
        )
    }

    override fun mapToEntity(domain: Envelope): EnvelopeEntity {
        return EnvelopeEntity(
            id = domain.id,
            name = domain.name,
            icon =getIconName(domain.icon),
            total = domain.total
        )
    }

    /**
     * Map from a list of entities to a list of domain models
     */
    fun mapFromEntityList(entities: List<EnvelopeEntity>): List<Envelope>{
        return entities.map { mapFromEntity(it) }
    }

    fun getIcon(iconName: String): Int{
        return when(iconName){
            "gas" -> R.drawable.gas_classification
            else -> R.drawable.gas_classification
        }
    }

    fun getIconName(drawableId: Int): String{
        return when(drawableId) {
            R.drawable.gas_classification -> "gas"
            else -> "gas"
        }
    }
}