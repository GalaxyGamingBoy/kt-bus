package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * A trip headsign
 */
@Serializable
public data class TripHeadsign(val routeId: RouteId, val headsign: String, val shapeId: ShapeId)
