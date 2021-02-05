package index

import index.store.store
import kotlinext.js.require.context
import kotlinext.js.requireAll
import kotlinx.browser.document
import react.dom.render
import react.redux.provider
import kotlin.js.RegExp

fun main() {
  requireAll(context("src/style", true, js("/\\.css$/").unsafeCast<RegExp>()))
  render(document.getElementById("root")) {
    provider(store) {
      app()
    }
  }
}
