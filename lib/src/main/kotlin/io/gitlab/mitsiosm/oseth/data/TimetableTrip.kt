package io.gitlab.mitsiosm.oseth.data

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * A timetable trip
 */
@Serializable
public data class TimetableTrip(
    public val id: TripId,
    public val headsign: String,
    public val shapeId: ShapeId,
    public val route: TimetableRoute,
    public val arrivalTime: LocalTime,
    public val departureTime: LocalTime,
    public val delay: UInt,
    public val monitored: Boolean,
    public val departureInMinutes: JsonElement?,
    public val arrivalInMinutes: JsonElement?,
    public val vehicle: Vehicle?,
)