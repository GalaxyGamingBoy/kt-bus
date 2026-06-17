package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * An OSETH route
 */
@Serializable
public data class Route(public val id: RouteId,
                        public val shortName: String,
                        public val longName: String,
                        public val color: String,
                        public val tripHeadsigns: List<TripHeadsign>
)
