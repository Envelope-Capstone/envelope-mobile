package com.joesamyn.envelope.repositories.mappers

import com.joesamyn.envelope.R
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.repositories.entities.EnvelopeEntity
import com.joesamyn.envelope.util.IEntityDomainMapper
import javax.inject.Inject

@Suppress("DUPLICATE_LABEL_IN_WHEN")
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

    private fun getIcon(iconName: String): Int{
        return when(iconName){
            "gas" -> R.drawable.gas_classification
            "merchandise" -> R.drawable.ic_supermarket
            "deptStores" -> R.drawable.ic_deptstores
            "education" -> R.drawable.ic_education
            "restaurants" -> R.drawable.ic_restaurant
            "supermarkets" -> R.drawable.ic_supermarket
            "automotive" -> R.drawable.ic_auto
            else -> R.drawable.gas_classification
        }
    }

    private fun getIconName(drawableId: Int): String{
        return when(drawableId) {
            R.drawable.gas_classification -> "gas"
            R.drawable.ic_supermarket -> "merchandise"
            R.drawable.ic_deptstores -> "deptStores"
            R.drawable.ic_education -> "education"
            R.drawable.ic_restaurant -> "restaurants"
            R.drawable.ic_supermarket -> "supermarkets"
            R.drawable.ic_auto -> "automotive"
            else -> "gas"
        }
    }
}