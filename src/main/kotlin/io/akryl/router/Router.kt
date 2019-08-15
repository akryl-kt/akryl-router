@file:Suppress("FunctionName")

package io.akryl.router

import io.akryl.Component
import io.akryl.react.FunctionalComponent
import io.akryl.react.ReactNode
import io.akryl.react.createElement
import kotlin.js.json

@JsModule("react-router-dom")
private external object ReactRouter {
  val BrowserRouter: FunctionalComponent
  val Route: FunctionalComponent
  val Link: FunctionalComponent
}

fun BrowserRouter(vararg children: ReactNode): ReactNode {
  return createElement(
    ReactRouter.BrowserRouter,
    null,
    *children.map { Component.build(it) }.toTypedArray()
  )
}

fun Route(
  path: String,
  exact: Boolean = false,
  component: () -> Component
): ReactNode {
  val factory = { Component.build(component()) }
  return createElement(
    ReactRouter.Route,
    json(
      "path" to path,
      "exact" to exact,
      "component" to factory
    )
  )
}

fun Link(
  to: String,
  child: ReactNode
): ReactNode {
  return createElement(
    ReactRouter.Link,
    json(
      "to" to to
    ),
    Component.build(child)
  )
}
