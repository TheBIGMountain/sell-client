package index.pages.order

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderTop
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.orderStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child(".form") {
      children {
        firstChild { borderTop(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1)) }
        padding(16.px)
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        lastChild { borderBottom = "none" }
        child("span") {
          display = Display.inlineBlock
          width = 80.px
        }
      }
    }
    child(".title") {
      children {
        display = Display.inlineBlock
        verticalAlign = VerticalAlign.middle
      }
      lineHeight = LineHeight(40.px.value)
      borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
      child("img") {
        margin(0.px, 15.px)
      }
    }
    child("ul .food") {
      padding(16.px)
      overflow = Overflow.hidden
      children {
        firstChild { float = Float.left }
        lastChild { float = Float.right }
      }
      borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    }
    child(".amount-wrapper") {
      lineHeight = LineHeight(40.px.value)
      overflow = Overflow.hidden
      position = Position.absolute
      bottom = 0.px
      width = 100.pct
      children {
        color = Color.white
        firstChild {
          float = Float.left
          paddingLeft = 20.px
          backgroundColor = Color("#141d27")
          put("width", "calc(70% - 20px)")
        }
        lastChild {
          float = Float.right
          width = 30.pct
          textAlign = TextAlign.center
          backgroundColor = Color("#00b43c")
        }
      }
    }
  }
  func()
}