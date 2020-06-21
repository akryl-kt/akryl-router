package io.akryl.router

@JsModule("history")
@JsNonModule
external class History {
  companion object {
    fun createBrowserHistory(): History
    fun createHashHistory(): History
    fun createMemoryHistory(): History
  }

  val length: Int
  val location: Location

  fun push(path: String, state: dynamic = definedExternally)
  fun push(location: LocationDescriptor)
  fun replace(path: String, state: dynamic = definedExternally)
  fun replace(location: LocationDescriptor)
  fun go(n: Int)
  fun goBack()
  fun goForward()
}

class Location(
  val pathname: String,
  val search: String,
  val state: dynamic,
  val hash: String,
  val key: String?
)

class LocationDescriptor(
  val pathname: String? = undefined,
  val search: String? = undefined,
  val state: dynamic = undefined,
  val hash: String? = undefined,
  val key: String? = undefined
)
