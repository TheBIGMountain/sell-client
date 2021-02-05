@file:JsModule("react-router-dom")
package index.modules

@JsName("useHistory")
external fun useHistory(): History

@JsName("useParams")
external val useParams: () -> dynamic

external interface History {
  fun push(url: String)
}