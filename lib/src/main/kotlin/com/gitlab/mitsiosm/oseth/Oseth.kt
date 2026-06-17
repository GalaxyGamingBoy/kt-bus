package com.gitlab.mitsiosm.oseth

import com.gitlab.mitsiosm.oseth.data.ApiRoute
import com.gitlab.mitsiosm.oseth.data.ApiRouteInfo
import com.gitlab.mitsiosm.oseth.data.Language
import com.gitlab.mitsiosm.oseth.data.Route
import com.gitlab.mitsiosm.oseth.data.RouteId
import com.gitlab.mitsiosm.oseth.data.RouteInfo
import com.gitlab.mitsiosm.oseth.data.ShapeId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.*
import io.ktor.serialization.kotlinx.json.json

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
     */
    public suspend fun getRouteInfo(routeId: RouteId, shapeId: ShapeId, language: Language = Language.GREEK): Result<RouteInfo> {
        try {
            val info: ApiRouteInfo = client.get(TelematicsApi.Route.Info(
                TelematicsApi.Route(id = routeId), shapeId, language)).body()
            return Result.success(info.data)
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        }
    }
}
