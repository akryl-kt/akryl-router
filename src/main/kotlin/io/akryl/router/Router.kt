@file:Suppress("FunctionName")

package io.akryl.router

import io.akryl.Component
import io.akryl.html.children
import io.akryl.html.classes
import io.akryl.react.Context
import io.akryl.react.FunctionalComponent
import io.akryl.react.ReactNode
import io.akryl.react.createElement
import io.akryl.useContext
import kotlin.js.json

@JsModule("react-router-dom")
private external object ReactRouter {
  val BrowserRouter: FunctionalComponent
  val Route: FunctionalComponent
  val Link: FunctionalComponent
  val Redirect: FunctionalComponent

  @Suppress("ObjectPropertyName")
  val __RouterContext: Context<RouteChildrenProps<Any>>
}

external interface Match<P> {
  val params: P
  val isExact: Boolean
  val path: String
  val url: String
}

external interface RouteLocation<S> {
  var hash: String
  var pathname: String
  var search: String
  var state: S
}

class LocationDescriptorObject<S>(
  var pathname: String? = undefined,
  var search: String? = undefined,
  var state: S? = undefined,
  var hash: String? = undefined,
  var key: String? = undefined
)

external interface RouteHistory<S> {
  var length: Int
  var location: RouteLocation<S>

  fun push(path: String)
  fun push(LocationDescriptorObject: LocationDescriptorObject<S>)
  fun go(n: Int)
  fun goBack()
  fun goForward()
}

external interface RouteChildrenProps<P> {
  var history: RouteHistory<Any?>
  var location: RouteLocation<Any?>
  var match: Match<P>
}

fun BrowserRouter(vararg children: ReactNode): ReactNode {
  return createElement(
    ReactRouter.BrowserRouter,
    null,
    *children.map { Component.build(it) }.toTypedArray()
  )
}

fun <P> Route(
  component: ((props: RouteChildrenProps<P>) -> Component)? = undefined,
  render: (() -> ReactNode)? = undefined,
  children: List<ReactNode>? = undefined,
  path: String? = undefined,
  exact: Boolean? = undefined,
  sensitive: Boolean? = undefined,
  strict: Boolean? = undefined
): ReactNode {
  val componentWrapper = component?.let { c ->
    { props: RouteChildrenProps<P> -> Component.build(c(props)) }
  }

  val renderWrapper = render?.let { r ->
    { Component.build(r()) }
  }

  return createElement(
    ReactRouter.Route,
    json(
      "component" to componentWrapper,
      "render" to renderWrapper,
      "path" to path,
      "exact" to exact,
      "sensitive" to sensitive,
      "strict" to strict
    ),
    *children(children = children).toTypedArray()
  )
}

fun Route(
  component: (() -> Component)? = undefined,
  render: (() -> ReactNode)? = undefined,
  children: List<ReactNode>? = undefined,
  path: String? = undefined,
  exact: Boolean? = undefined,
  sensitive: Boolean? = undefined,
  strict: Boolean? = undefined
): ReactNode {
  val componentInner: ((props: RouteChildrenProps<Unit>) -> Component)? = if (component !== undefined) {
    { component() }
  } else {
    undefined
  }
  return Route(
    component = componentInner,
    render = render,
    children = children,
    path = path,
    exact = exact,
    sensitive = sensitive,
    strict = strict
  )
}

fun Link(
  to: String,
  replace: Boolean? = undefined,
  clazz: CharSequence? = null,
  classes: List<CharSequence>? = null,
  text: String? = null,
  child: ReactNode? = null,
  children: List<ReactNode>? = null
): ReactNode {
  return createElement(
    ReactRouter.Link,
    json(
      "to" to to,
      "replace" to replace,
      "className" to classes(clazz, classes)
    ),
    *children(text, child, children).toTypedArray()
  )
}

fun Link(
  to: LocationDescriptorObject<Any?>,
  replace: Boolean? = undefined,
  clazz: CharSequence? = null,
  classes: List<CharSequence>? = null,
  text: String? = null,
  child: ReactNode? = null,
  children: List<ReactNode>? = null
): ReactNode {
  return createElement(
    ReactRouter.Link,
    json(
      "to" to to,
      "replace" to replace,
      "className" to classes(clazz, classes)
    ),
    *children(text, child, children).toTypedArray()
  )
}

fun Redirect(
  to: String,
  push: Boolean? = undefined,
  from: String? = undefined,
  exact: Boolean? = undefined,
  strict: Boolean? = undefined
): ReactNode {
  return createElement(
    ReactRouter.Redirect,
    json(
      "to" to to,
      "push" to push,
      "from" to from,
      "exact" to exact,
      "strict" to strict
    )
  )
}

fun Redirect(
  to: LocationDescriptorObject<Any?>,
  push: Boolean? = undefined,
  from: String? = undefined,
  exact: Boolean? = undefined,
  strict: Boolean? = undefined
): ReactNode {
  return createElement(
    ReactRouter.Redirect,
    json(
      "to" to to,
      "push" to push,
      "from" to from,
      "exact" to exact,
      "strict" to strict
    )
  )
}

fun useRouter() = useContext(ReactRouter.__RouterContext)