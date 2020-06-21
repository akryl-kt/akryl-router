package io.akryl.router

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HistoryTest {
  @Test
  fun testBrowserHistory() {
    testHistory(History.createBrowserHistory())
  }

  @Test
  fun testMemoryHistory() {
    testHistory(History.createMemoryHistory())
  }

  @Test
  fun testHashHistory() {
    testHistory(History.createHashHistory())
  }

  @Suppress("USELESS_IS_CHECK")
  private fun testHistory(history: History) {
    assertTrue(history.length is Int)
    assertNotNull(history.location)
    assertTrue(history.location.pathname is String)

    history.push("/foo?bar=baz")

    assertTrue(history.location.search is String)

    history.replace("/qux")
    history.goBack()
    history.goForward()
    history.go(-1)
  }
}
