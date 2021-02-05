package index.pages.index.content.goods

import index.style.bgImage
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderLeft
import kotlinx.html.DIV
import kotlinx.html.LI
import react.RBuilder
import styled.*

private val imgs3 = arrayOf(
  "/resources/brand/decrease_3@2x.png",
  "/resources/brand/discount_3@2x.png",
  "/resources/brand/guarantee_3@2x.png",
  "/resources/brand/invoice_3@2x.png",
  "/resources/brand/special_3@2x.png",
)


fun RBuilder.goodsStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.fixed
    display = Display.flex
    top = 174.px
    bottom = 46.px
    width = 100.pct
    marginTop = 1.px
    children { overflow = Overflow.auto }
    child(".menu-wrapper::-webkit-scrollbar") { display = Display.none }
    child(".foods-wrapper::-webkit-scrollbar") { display = Display.none }
    child(".menu-wrapper") {
      width = 114.px
      backgroundColor = Color("#f3f5f7")
    }
    child(".foods-wrapper") {
      width = 100.pct
      child("ul .food-list") {
        child(".title") {
          paddingLeft = 14.px
          height = 26.px
          lineHeight = LineHeight(height.value)
          borderLeft(2.px, BorderStyle.solid, Color("#d9dde1"))
          fontSize = 12.px
          color = rgb(147, 153, 159)
          backgroundColor = Color("#f3f5f7")
        }
        child("ul .food-item") {
          position = Position.relative
          display = Display.flex
          marginTop = 18.px
          paddingBottom = 18.px
          paddingLeft = 18.px
          borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
          lastChild { borderBottom = "none"; marginBottom = 0.px }
          child(".content") {
            marginLeft = 10.px
            child(".name") {
              margin(2.px, 0.px, 8.px)
              height = 14.px
              lineHeight = LineHeight(height.value)
              fontSize = 14.px
              color = rgb(7, 17, 27)
            }
            child(".extra") {
              height = 10.px
              lineHeight = LineHeight(height.value)
              fontSize = 10.px
              color = rgb(147, 153, 159)
              children { firstChild { marginRight = 12.px } }
            }
            child(".count-wrapper") {
              position = Position.absolute
              right = 0.px
              bottom = 12.px
            }
          }
        }
      }
    }
  }
  func()
}

fun RBuilder.price(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    fontWeight = FontWeight.w700
    lineHeight = LineHeight(24.px.value)
    children { firstChild {
      fontSize = 14.px
      color = rgb(240, 20, 20)
    } }
  }
  func()
}

fun RBuilder.menuItem(isShow: Boolean, func: StyledDOMBuilder<LI>.() -> Unit) = styledLi {
  css {
    display = Display.flex
    alignItems = Align.center
    height = 54.px
    paddingLeft = 12.px
    borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    if (isShow) {
      marginTop = (-1).px
      backgroundColor = Color.white
      fontWeight = FontWeight.w700
      child(".text") {
        border = "none"
      }
    }
    child(".text") {
      width = 56.px
      fontSize = 12.px
    }
  }
  func()
}

fun RBuilder.icon3(type: Int) = styledSpan {
  css {
    width = 12.px
    height = 12.px
    marginRight = 4.px
    marginBottom = 2.px
    bgImage(imgs3[type])
  }
}
