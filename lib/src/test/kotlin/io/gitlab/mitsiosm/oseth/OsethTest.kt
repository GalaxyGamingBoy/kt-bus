package io.gitlab.mitsiosm.oseth

import io.gitlab.mitsiosm.oseth.data.Language
import io.gitlab.mitsiosm.oseth.data.Route
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.todayIn
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import kotlin.time.Clock

class OsethTest {
    lateinit var library: Oseth

    @BeforeEach
    fun setUp() {
        library = Oseth()
    }
    
    suspend fun getRoute(): Route {
        val routes = library.getRoutes(1u, 1u).getOrThrow()
        return routes.first()
    }

    @Test
    fun `ensure getRoutes returns`() = runTest { 
        val routes = library.getRoutes()
        
        assertTrue(routes.isSuccess)
        assertTrue(routes.getOrThrow().isNotEmpty())
    }
    
    @Test
    fun `ensure getRoutes followsSize`() = runTest {
        val routes = library.getRoutes(size = 10u)
        
        assertTrue(routes.isSuccess)
        assertTrue(routes.getOrThrow().isNotEmpty())
        assertTrue(routes.getOrThrow().size <= 10)
    }
    
    @Test
    fun `ensure getRoutes followsPage`() = runTest {
        val routesPage1 = library.getRoutes(size = 10u, page = 1u)
        val routesPage2 = library.getRoutes(size = 20u, page = 2u)
        
        assertTrue(routesPage1.isSuccess)
        assertTrue(routesPage1.getOrThrow().isNotEmpty())
        assertTrue(routesPage2.isSuccess)
        assertTrue(routesPage2.getOrThrow().isNotEmpty())

        assertTrue(routesPage1.getOrThrow() != routesPage2.getOrThrow())
    }

    @Test
    fun `ensure getRoutes errorIfNoSize`() = runTest {
        val routes = library.getRoutes(size = 0u)
        assertTrue(routes.isFailure)
    }
    
    @Test
    fun `ensure getRoutes errorIfNoPage`() = runTest {
        val routes = library.getRoutes(page = 0u)
        assertTrue(routes.isFailure)
    }
    
    @Test
    fun `ensure getRouteInfo returns`() = runTest {
        val route = getRoute()
        val info = library.getRouteInfo(route.id, route.tripHeadsigns[0].shapeId)
        
        assertTrue(info.isSuccess)
    }
    
    @Test
    fun `ensure getRouteInfo followsLanguage`() = runTest {
        val route = getRoute()
        
        val infoGr = library.getRouteInfo(route.id, route.tripHeadsigns[0].shapeId, Language.GREEK)
        val infoEn = library.getRouteInfo(route.id, route.tripHeadsigns[0].shapeId, Language.ENGLISH)
        
        assertTrue(infoGr.isSuccess)
        assertTrue(infoEn.isSuccess)
        
        assertTrue(infoGr.getOrThrow().headsign != infoEn.getOrThrow().headsign)
    }
    
    @Test
    fun `ensure getTimetable returns`() = runTest {
        val route = getRoute()

        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val midnight = today.atStartOfDayIn(TimeZone.currentSystemDefault())
        
        val timetable = library.getTimetable(route.id, route.tripHeadsigns[0].shapeId, midnight, Language.GREEK)
        
        assertTrue(timetable.isSuccess)
    }
    
    @Test
    fun `ensure getTimetable followsLanguage`() = runTest {
        val route = getRoute()
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val midnight = today.atStartOfDayIn(TimeZone.currentSystemDefault())

        val timetableGr = library.getTimetable(route.id, route.tripHeadsigns[0].shapeId, midnight, Language.GREEK)
        val timetableEn = library.getTimetable(route.id, route.tripHeadsigns[0].shapeId, midnight, Language.ENGLISH)
        
        assertTrue(timetableGr.isSuccess)
        assertTrue(timetableEn.isSuccess)
        
        assertTrue(timetableGr.getOrThrow().headsign != timetableEn.getOrThrow().headsign)
    }
    
    @Test
    fun `ensure getTimetableForToday returns`() = runTest {
        val route = getRoute()
        val timetable = library.getTimetableForToday(route.id, route.tripHeadsigns[0].shapeId, Language.GREEK)
        
        assertTrue(timetable.isSuccess)
    }
}
