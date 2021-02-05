package index.components.split

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderTop
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv

private val split = functionalComponent<RProps> {
  styledDiv {
    css {
      width = 100.pct
      height = 16.px
      borderTop(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
      borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
      backgroundColor = Color("#f3f5f7")
    }
  }
}

fun RBuilder.split() = child(split)