package index.ajax

import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.js.json

const val index = "http://\$PRIVATE_NETWORK"

interface ResultVo<T> {
  var data: T
}

enum class Method(val type: String) {
  GET("GET"), POST("POST")
}

suspend fun <T> Method.ajax(url: String, json: Any? = null): Flow<T> {
  return window.fetch("${index}$url", jsObject {
    method = type
    headers = json("Content-Type" to "application/json")
    if (json != null) body = JSON.stringify(json)
  }).await().json().await().unsafeCast<T>().let { flowOf(it) }
}