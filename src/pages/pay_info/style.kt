package index.pages.pay_info

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.border
import kotlinx.css.properties.borderBottom
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

private fun CSSBuilder.common() {
  children {
    padding(10.px)
    borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    lastChild { borderBottom = "none" }
    not(":first-child") {
      children { lastChild { float = Float.right } }
    }
  }
}

fun RBuilder.payInfoStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child(".user-info") {
      paddingBottom = 30.px
      textAlign = TextAlign.center
      child("img") {
        margin(10.px)
        borderRadius = 50.pct
      }
      child("a") {
        marginTop = 10.px
        border(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        display = Display.inlineBlock
        width = 100.px
        height = 30.px
        lineHeight = LineHeight(height.value)
      }
    }
    child(".product-info") {
      child(".title img") { marginRight = 10.px }
      common()
    }
    child(".delivery-info") { common() }
    child(".order-info") { common() }
  }
  func()
}