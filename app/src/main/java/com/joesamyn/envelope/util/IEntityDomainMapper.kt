package com.joesamyn.envelope.util

interface IEntityDomainMapper<Entity, Domain> {

    /**
     * Map from entity to domain model
     */
    fun mapFromEntity(entity: Entity): Domain

    /**
     * Map from domain to entity model
     */
    fun mapToEntity(domain: Domain): Entity
}