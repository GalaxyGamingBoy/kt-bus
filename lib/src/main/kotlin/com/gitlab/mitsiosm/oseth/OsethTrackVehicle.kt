package com.gitlab.mitsiosm.oseth

import com.gitlab.mitsiosm.oseth.data.Coordinates
import com.gitlab.mitsiosm.oseth.data.RouteId
import com.gitlab.mitsiosm.oseth.data.ShapeId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Background vehicle location tracker with a channel
 * 
 * @param routeId route to track
 * @param shapeId shape to track
 * @param period time to refresh
 */
public class OsethTrackVehicle(public val routeId: RouteId,
                               public val shapeId: ShapeId,
                               public val period: Duration = 1000.milliseconds,
) {
    /**
     * Broadcast channel with GPS coordinates.
     * Argument is a list of coordinates - multiple vehicle support
     */
    public val channel: Channel<List<Coordinates>> = Channel()
    
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Default)
    private val oseth = Oseth()
    
    init {
        task()
    }
    
    private fun task() {
        scope.launch {
            while (isActive) {
                track()
                delay(period)
            }
        }
    }
    
    private suspend fun track() {
        val info = oseth.getRouteInfo(routeId, shapeId)
        if (info.isFailure) return
        val vehicles = info.getOrThrow().vehicles.map { Coordinates(it.latitude, it.longitude) }
        channel.send(vehicles)
    }

    /**
     * Stop tracking
     */
    public fun stop() {
        job.cancel()
        channel.close()
    }
}