package com.gitlab.mitsiosm.oseth

import io.ktor.resources.Resource

@Resource("/telematics-api")
internal class TelematicsApi {
    @Resource("route")
    internal class Routes(val parent: TelematicsApi = TelematicsApi(), val size: UInt = 1000u, val page: UInt = 1u)
}