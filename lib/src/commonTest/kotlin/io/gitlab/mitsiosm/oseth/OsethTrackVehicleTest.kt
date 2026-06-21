package io.gitlab.mitsiosm.oseth

import io.gitlab.mitsiosm.oseth.data.Route
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class OsethTrackVehicleTest {
    lateinit var library: Oseth

    @BeforeTest
    fun setUp() {
        library = Oseth()
    }
    
    suspend fun getRoute(): Route {
        val routes = library.getRoutes(1u, 1u).getOrThrow()
        return routes.first()
    }

    @Test
    fun `ensure OsethTrackVehicle works`() = runTest { 
        val route = getRoute()
        val tracker = OsethTrackVehicle(route.id, route.tripHeadsigns[0].shapeId)

        var index = 0
        launch { 
            for (gps in tracker.channel) {
                if (index == 4) 
                    tracker.stop()
                index++
            }
        }.join()

        assertEquals(index, 5)
    }
}
