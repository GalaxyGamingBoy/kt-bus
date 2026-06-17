package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiRouteInfo(
    internal val data: RouteInfo,
    internal val error: String,

    @SerialName("status_code")
    internal val statusCode: UInt
)
