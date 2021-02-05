package index.components.star

import index.style.bgImage
import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.starStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    child(".star-item") { flex(1.0) }
    starImg(10, 10, 24)
    starImg(15, 15, 36)
    starImg(20, 20, 48)
  }
  func()
}

private fun CSSBuilder.starImg(w: Int, h: Int, size: Int) {
  child(".star-${size}") {
    width = w.px
    height = h.px
  }
  child(".star-${size}.on") { bgImage("/resources/star/star${size}_on@2x.png") }
  child(".star-${size}.half") { bgImage("/resources/star/star${size}_half@2x.png") }
  child(".star-${size}.off") { bgImage("/resources/star/star${size}_off@2x.png") }
}