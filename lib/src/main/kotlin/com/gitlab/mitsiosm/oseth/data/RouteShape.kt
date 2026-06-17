package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * The GeoShape of a route
 */
@Serializable
public data class RouteShape(
    public val id: ShapeId,
    public val color: Color,
    public val lineString: String,
)
