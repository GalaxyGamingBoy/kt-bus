package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * An OSETH trip headsign
 */
@Serializable
public data class TripHeadsign(val routeId: RouteId, val headsign: String, val shapeId: ShapeId)
