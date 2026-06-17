package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * A vehicle servicing this route
 */
@Serializable
public data class Vehicle(
    public val id: VehicleId,
    public val bearing: Double,
    public val latitude: Double,
    public val longitude: Double,
)