package io.akryl.router

import io.akryl.dom.css.CssStyle
import io.akryl.dom.html.concatChildrenToArray
import io.akryl.dom.html.concatStyle
import react.Component
import react.React
import react.ReactElement
import kotlin.js.json

@JsModule("react-router-dom")
@JsNonModule
external object ReactRouterDom {
    val BrowserRouter: Component<dynamic>
    val HashRouter: Component<dynamic>
    val Link: Component<dynamic>
}

@JsModule("react-router")
@JsNonModule
external object ReactRouter {
    val MemoryRouter: Component<dynamic>
    val Redirect: Component<dynamic>
    val Route: Component<dynamic>
    val Switch: Component<dynamic>
}

class LocationDescriptor(
    val pathname: String? = undefined,
    val search: String? = undefined,
    val state: dynamic = undefined,
    val hash: String? = undefined,
    val key: String? = undefined
)

class Location(
    val pathname: String,
    val search: String,
    val state: dynamic,
    val hash: String,
    val key: String?
)

class Match(
    val params: dynamic,
    val isExact: Boolean,
    val path: String,
    val url: String
)

class RouteComponentProps(
    val history: dynamic,
    val location: Location,
    val match: Match
)

fun browserRouter(
    children: List<ReactElement<*>> = emptyList()
) = React.createElement(
    ReactRouterDom.BrowserRouter,
    null,
    *children.toTypedArray()
)

fun hashRouter(
    children: List<ReactElement<*>> = emptyList()
) = React.createElement(
    ReactRouterDom.HashRouter,
    null,
    *children.toTypedArray()
)

fun memoryRouter(
    initialEntries: List<LocationDescriptor>? = null,
    initialIndex: Int? = null,
    children: List<ReactElement<*>> = emptyList()
) = React.createElement(
    ReactRouter.MemoryRouter,
    json(
        "initialIndex" to initialIndex,
        "initialEntries" to initialEntries?.toTypedArray()
    ),
    *children.toTypedArray()
)

fun redirect(
    to: LocationDescriptor,
    push: Boolean? = undefined,
    from: String? = undefined,
    path: String? = undefined,
    exact: Boolean? = undefined,
    strict: Boolean? = undefined
) = React.createElement(
    ReactRouter.Redirect,
    json(
        "to" to to,
        "push" to push,
        "from" to from,
        "path" to path,
        "exact" to exact,
        "strict" to strict
    )
)

fun route(
    render: ((props: RouteComponentProps) -> ReactElement<*>)? = undefined,
    children: List<ReactElement<*>> = emptyList(),
    path: String? = undefined,
    exact: Boolean? = undefined,
    sensitive: Boolean? = undefined,
    strict: Boolean? = undefined
) = React.createElement(
    ReactRouter.Route,
    json(
        "render" to render,
        "path" to path,
        "exact" to exact,
        "sensitive" to sensitive,
        "strict" to strict
    ),
    *children.toTypedArray()
)

fun switch(
    children: List<ReactElement<*>> = emptyList()
) = React.createElement(
    ReactRouter.Switch,
    null,
    *children.toTypedArray()
)

fun link(
    to: LocationDescriptor,
    replace: Boolean? = undefined,
    className: CharSequence? = null,
    css: CssStyle? = null,
    children: List<ReactElement<*>> = emptyList(),
    child: ReactElement<*>? = null,
    text: String? = null
) = React.createElement(
    ReactRouterDom.Link,
    json(
        "to" to to,
        "replace" to replace,
        "className" to concatStyle(css, className)
    ),
    children = *concatChildrenToArray(children, child, text)
)