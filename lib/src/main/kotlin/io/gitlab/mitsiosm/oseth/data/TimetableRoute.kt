package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * A route in the timetable
 */
@Serializable
public data class TimetableRoute(
    public val id: RouteId,
    public val shortName: String,
    public val longName: String,
    public val color: String
)
