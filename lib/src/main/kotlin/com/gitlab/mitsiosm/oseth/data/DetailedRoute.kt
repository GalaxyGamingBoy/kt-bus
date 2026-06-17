package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * Detailed information regarding a route
 */
@Serializable
public data class DetailedRoute(
    public val id: RouteId,
    public val shortName: String,
    public val longName: String,
    public val color: String,
    public val headsign : String,
    public val shape: RouteShape,
    public val stops: List<Stop>,
    public val vehicles: List<Vehicle>,
)
