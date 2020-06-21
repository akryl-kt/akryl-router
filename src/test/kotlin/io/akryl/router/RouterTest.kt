package io.akryl.router

import io.akryl.dom.html.div
import react.React
import react.ReactElement
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RouterTest {
    @Test
    fun testRouter() {
        assertIsComponent(
            router()
        )
    }

    @Test
    fun testMemoryRouter() {
        assertIsComponent(
            memoryRouter()
        )
    }

    @Test
    fun testRedirect() {
        assertIsComponent(
            redirect(
                to = LocationDescriptor(pathname = "/test")
            )
        )
    }

    @Test
    fun testRoute() {
        assertIsComponent(
            route(
                path = "/test",
                render = { div() }
            )
        )
    }

    @Test
    fun testSwitch() {
        assertIsComponent(
            switch()
        )
    }

    @Test
    fun testBrowserRouter() {
        assertIsComponent(
            browserRouter()
        )
    }

    @Test
    fun testHashRouter() {
        assertIsComponent(
            hashRouter()
        )
    }

    @Test
    fun testLink() {
        assertIsComponent(
            link(to = LocationDescriptor(pathname = "/test"))
        )
    }

    private fun assertIsComponent(element: ReactElement<*>) {
        assertNotNull(element.type)
        assertTrue(React.isValidElement(element))
    }
}