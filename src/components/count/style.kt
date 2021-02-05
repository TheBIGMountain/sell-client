package index.components.count

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.countStyle(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    alignItems = Align.center
    fontSize = 20.px
    color = rgb(0, 160, 220)
    children { padding(6.px) }
    zIndex = 10
    child(".cart-add") { position = Position.relative }
    child(".cart-count") {
      padding(0.px)
      fontSize = 14.px
      width = 12.px
      lineHeight = LineHeight(24.px.value)
      textAlign = TextAlign.center
      color = rgb(147, 153, 159)
    }
    if (isShow) {
      child(".cart-decrease.icon-remove_circle_outline") {
        opacity = 1
        transform { translateX(0.px); rotate(0.deg) }
      }
      child(".cart-count") { opacity = 1 }
    }
    else {
      child(".cart-decrease.icon-remove_circle_outline") {
        opacity = 0
        transform { translateX(24.px); rotate(180.deg) }
      }
      child(".cart-count") { opacity = 0 }
    }
    children { transition("all", 0.5.s) }
  }
  func()
}

fun RBuilder.ball(isShow: Boolean, clientRect: dynamic) = styledDiv {
  css {
    position = Position.absolute
    width = 18.px
    height = 18.px
    borderRadius = 50.pct
    backgroundColor = rgb(0, 160, 220)
    transform { translate(0.0.px, (-18).px) }
    opacity = 0
    if (isShow) {
      opacity = 1
      val transRight = (-clientRect.left.unsafeCast<Int>() + 30).px
      val transDown = (window.innerHeight - clientRect.top.unsafeCast<Int>() - 60).px
      transform { translate(transRight, transDown) }
      transition("transform", 0.5.s)
    }
  }
}



