package com.gitlab.mitsiosm.oseth

import com.gitlab.mitsiosm.oseth.data.Language
import com.gitlab.mitsiosm.oseth.data.RouteId
import com.gitlab.mitsiosm.oseth.data.ShapeId
import io.ktor.resources.Resource

@Resource("/telematics-api")
internal class TelematicsApi {
    @Resource("route")
    internal class Routes(val parent: TelematicsApi = TelematicsApi(), val size: UInt = 1000u, val page: UInt = 1u)
    
    @Resource("route/{id}")
    internal class Route(val parent: TelematicsApi = TelematicsApi(), val id: RouteId) {
        @Resource("info")
        internal class Info(val parent: Route, val shapeId: ShapeId, val language: Language = Language.GREEK)
        
    }
}