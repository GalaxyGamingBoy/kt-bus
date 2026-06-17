package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The timetable entry for a specific instant
 */
@Serializable
public data class Timetable(
    public val id: RouteId,
    public val shortName: String,
    public val longName: String,
    public val color: String,
    public val headsign: String,
    @SerialName("stop")
    public val finalStop: TimetableStop, 
    public val trips: List<TimetableTrip>
)
