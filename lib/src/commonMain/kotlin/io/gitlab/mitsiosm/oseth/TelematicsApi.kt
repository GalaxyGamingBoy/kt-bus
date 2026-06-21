package io.gitlab.mitsiosm.oseth

import io.gitlab.mitsiosm.oseth.data.Language
import io.gitlab.mitsiosm.oseth.data.RouteId
import io.gitlab.mitsiosm.oseth.data.ShapeId
import io.gitlab.mitsiosm.oseth.serializers.InstantSerializer
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.Instant

@Resource("/telematics-api")
internal class TelematicsApi {
    @Resource("route")
    internal class Routes(val parent: TelematicsApi = TelematicsApi(), val size: UInt = 1000u, val page: UInt = 1u)
    
    @Resource("route/{id}")
    internal class Route(val parent: TelematicsApi = TelematicsApi(), val id: RouteId) {
        @Resource("info")
        internal class Info(val parent: Route, val shapeId: ShapeId, val language: Language = Language.GREEK)
        
        @Resource("timetable")
        internal class Timetable(val parent: Route,
                                 val shapeId: ShapeId,
                                 @Serializable(with = InstantSerializer::class)
                                 val date: Instant = Clock.System.now(),
                                 val language: Language = Language.GREEK)
    }
}