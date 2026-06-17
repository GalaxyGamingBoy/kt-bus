package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiTimetable(
    internal val data: Timetable,
    internal val error: String,

    @SerialName("status_code")
    internal val statusCode: UInt
)
