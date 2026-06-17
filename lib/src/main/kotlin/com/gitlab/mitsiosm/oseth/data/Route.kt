package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * A route
 */
@Serializable
public data class Route(public val id: RouteId,
                        public val shortName: String,
                        public val longName: String,
                        public val color: Color,
                        public val tripHeadsigns: List<TripHeadsign>
)
