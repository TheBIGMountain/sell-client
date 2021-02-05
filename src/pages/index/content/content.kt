package index.pages.index.index.content

import index.pages.index.header.header
import index.pages.index.tab.tab
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

private interface ContentProps: RProps {
  var func: RBuilder.() -> Unit
}

private val content = functionalComponent<ContentProps> { props ->
  header()
  tab()
  props.func(this)
}

fun RBuilder.content(func: RBuilder.() -> Unit) = child(content) { attrs.func = func }
