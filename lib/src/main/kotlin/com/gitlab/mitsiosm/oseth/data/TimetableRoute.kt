package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

@Serializable
public data class TimetableRoute(
    public val id: RouteId,
    public val shortName: String,
    public val longName: String,
    public val color: String
)
