package com.gitlab.mitsiosm.oseth

import com.gitlab.mitsiosm.oseth.data.ApiRoute
import com.gitlab.mitsiosm.oseth.data.ApiRouteInfo
import com.gitlab.mitsiosm.oseth.data.ApiTimetable
import com.gitlab.mitsiosm.oseth.data.Language
import com.gitlab.mitsiosm.oseth.data.Route
import com.gitlab.mitsiosm.oseth.data.RouteId
import com.gitlab.mitsiosm.oseth.data.DetailedRoute
import com.gitlab.mitsiosm.oseth.data.ShapeId
import com.gitlab.mitsiosm.oseth.data.Timetable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.Instant

/**
 * OSETH API Call Instance 
 */
public class Oseth {
    private val client = HttpClient(CIO) {
        install(Logging)
        install(Resources)
        install(ContentNegotiation) {
            json()
        }
        
        defaultRequest {
            url("https://oseth.com.gr/el/")
        }
        
        expectSuccess = true
    }

    /**
     * Fetch all the routes
     * 
     * @param size How many routes to fetch
     * @param page Which page to fetch
     */
    public suspend fun getRoutes(size: UInt = 1000u, page: UInt = 1u): Result<List<Route>> {
        try {
            val routes: ApiRoute = client.get(TelematicsApi.Routes(size = size, page = page)).body()
            return Result.success(routes.data.routes.toList())
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        }
    }

    /**
     * Fetch detailed information regarding a route and shape
     * 
     * @param routeId The route to get the information from
     * @param shapeId The route shape to get the information from
     * @param language The language to get the information in
     */
    public suspend fun getRouteInfo(routeId: RouteId, shapeId: ShapeId, language: Language = Language.GREEK): Result<DetailedRoute> {
        try {
            val info: ApiRouteInfo = client.get(TelematicsApi.Route.Info(
                TelematicsApi.Route(id = routeId), shapeId, language)).body()
            return Result.success(info.data)
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        }
    }

    /**
     * Fetch the timetable for a route and shape
     * 
     * @param routeId The route to get the information from
     * @param shapeId The route shape to get the information from
     * @param from The instant to get the timetable from - Accounts for 24h
     * @param language The language to get the information in
     */
    public suspend fun getTimetable(routeId: RouteId, shapeId: ShapeId, from: Instant, language: Language = Language.GREEK): Result<Timetable> {
        try {
            val info: ApiTimetable = client.get(TelematicsApi.Route.Timetable(
                TelematicsApi.Route(id = routeId), shapeId, date = from, language
            )).body()
            return Result.success(info.data)
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        }
    }

    /**
     * Fetch the timetable for today
     * 
     * @param routeId The route to get the information from
     * @param shapeId The route shape to get the information from
     * @param language The language to get the information in
     * @param timezone The timezone to use that specifies the day
     */
    public suspend fun getTimetableForToday(routeId: RouteId, shapeId: ShapeId, language: Language = Language.GREEK, timezone: TimeZone = TimeZone.currentSystemDefault()): Result<Timetable> {
        val today = Clock.System.todayIn(timezone)
        val midnight = today.atStartOfDayIn(timezone)
        return getTimetable(routeId, shapeId, midnight, language)
    }
}
