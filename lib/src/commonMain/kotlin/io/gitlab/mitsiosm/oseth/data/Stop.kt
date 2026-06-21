package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * A route stop
 */
@Serializable
public data class Stop(
    public val id: StopId,
    public val code: String,
    public val name: String,
    public val latitude: Double,
    public val longitude: Double,
    public val sequence: UInt
)
