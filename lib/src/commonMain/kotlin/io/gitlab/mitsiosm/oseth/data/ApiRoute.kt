package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiRoute(
    internal val data: ApiRouteData,
    
    internal val error: String,
    
    @SerialName("status_code")
    internal val statusCode: UInt
    ) {
    @Serializable
    internal data class ApiRouteData(internal val routes: List<Route>, internal val total: ULong)
}
