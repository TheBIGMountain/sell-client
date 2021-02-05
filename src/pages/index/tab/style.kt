package index.pages.index.tab

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderBottom
import kotlinx.html.A
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledA
import styled.styledDiv

fun RBuilder.tabStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    width = 100.pct
    height = 40.px
    lineHeight = LineHeight(height.value)
    borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    child(".active") { color = rgb(240, 20, 20) }
  }
  func()
}

fun RBuilder.tabItem(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    flex(1.0)
    textAlign = TextAlign.center
    fontSize = 14.px
    color = rgb(77, 85, 93)
  }
  func()
}
