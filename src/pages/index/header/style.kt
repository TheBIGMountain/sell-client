package index.pages.index.header

import index.style.bgImage
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderTop
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.DIV
import react.RBuilder
import react.dom.div
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan

private val imgs1 = arrayOf(
  "/resources/brand/decrease_1@2x.png",
  "/resources/brand/discount_1@2x.png",
  "/resources/brand/guarantee_1@2x.png",
  "/resources/brand/invoice_1@2x.png",
  "/resources/brand/special_1@2x.png",
)

private val imgs2 = arrayOf(
  "/resources/brand/decrease_2@2x.png",
  "/resources/brand/discount_2@2x.png",
  "/resources/brand/guarantee_2@2x.png",
  "/resources/brand/invoice_2@2x.png",
  "/resources/brand/special_2@2x.png",
)

fun RBuilder.headerStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    color = Color.white
    position = Position.relative
    backgroundColor = rgba(7, 17, 27, 0.5)
    overflow = Overflow.hidden
    child(".content-wrapper") {
      position = Position.relative
      padding(24.px, 12.px, 18.px, 24.px)
      display = Display.flex
      child("img") { borderRadius = 2.px }
      child(".content") {
        marginLeft = 16.px
        child(".title") {
          margin(2.px, 0.px, 8.px)
          child(".brand") {
            display = Display.inlineBlock
            width = 30.px
            height = 18.px
            bgImage("/resources/brand/brand@2x.png")
            verticalAlign = VerticalAlign.top
            marginRight = 6.px
          }
          child(".name") {
            fontSize = 16.px
            lineHeight = LineHeight(18.px.value)
            fontWeight = FontWeight.bold
          }
        }
        child(".description") {
          marginBottom = 10.px
          lineHeight = LineHeight(12.px.value)
          fontSize = 12.px
        }
        child(".support") {
          display = Display.flex
          child(".text") {
            fontSize = 12.px
            lineHeight = LineHeight(12.px.value)
          }
        }
      }
      child(".support-count") {
        position = Position.absolute
        right = 12.px
        bottom = 18.px
        padding(0.px, 8.px)
        height = 24.px
        lineHeight = LineHeight(height.value)
        borderRadius = 14.px
        backgroundColor = rgba(0, 0, 0, 0.2)
        textAlign = TextAlign.center
        fontSize = 12.px
        child(".icon-keyboard_arrow_right") {
          lineHeight = LineHeight(24.px.value)
          marginLeft = 2.px
          verticalAlign = VerticalAlign.middle
        }
      }
    }
    child(".bulletin-wrapper") {
      height = 28.px
      lineHeight = LineHeight(height.value)
      padding(0.px, 12.px)
      overflow = Overflow.hidden
      whiteSpace = WhiteSpace.nowrap
      textOverflow = TextOverflow.ellipsis
      position = Position.relative
      backgroundColor = rgba(7, 17, 27, 0.2 )
      child(".bulletin-title") {
        display = Display.inlineBlock
        width = 22.px
        height = 12.px
        verticalAlign = VerticalAlign.middle
        bgImage("/resources/brand/bulletin@2x.png")
      }
      child(".bulletin-text") {
        paddingLeft = 4.px
        fontSize = 10.px
      }
      child(".icon-keyboard_arrow_right") {
        position = Position.absolute
        right = 8.px
        top = 8.px
        fontSize = 14.px
      }
    }
    child(".background") {
      position = Position.absolute
      top = 0.px
      left = 0.px
      width = 100.pct
      height = 100.pct
      zIndex = -1
      filter = "blur(10px)"
    }
  }
  func()
}

fun RBuilder.detail(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.fixed
    top = 0.px
    left = 0.px
    width = 100.pct
    height = 100.pct
    overflow = Overflow.auto
    zIndex = 100
    if (isShow) {
      visibility = Visibility.visible
      opacity = 1
      backgroundColor = rgba(7, 17, 27, 0.8)
    }
    else {
      visibility = Visibility.hidden
      opacity = 0
      backgroundColor = rgba(7, 17, 27, 0.0)
    }
    transition("all", 0.5.s)
    child(".detail-wrapper") {
      minHeight = 100.pct
      width = 100.pct
      display = Display.inlineBlock
      child(".detail-main") {
        marginTop = 64.px
        paddingBottom = 64.px
        child(".name") {
          lineHeight = LineHeight(16.px.value)
          textAlign = TextAlign.center
          fontSize = 16.px
          fontWeight = FontWeight("700")
        }
        child(".star-wrapper") {
          width = 200.px
          margin(18.px, LinearDimension.auto)
        }
        child(".supports") {
          width = 80.pct
          margin(0.px, LinearDimension.auto)
          child(".support-item") {
            padding(0.px, 12.px)
            marginBottom = 12.px
            lastChild { marginBottom = 0.px }
            child(".text") {
              lineHeight = LineHeight(16.px.value)
              fontSize = 12.px
            }
          }
        }
        child(".bulletin") {
          width = 80.pct
          margin(0.px, LinearDimension.auto)
          child(".content") {
            padding(0.px, 12.px)
            lineHeight = LineHeight(24.px.value)
            fontSize = 12.px
          }
        }
      }
    }
    child(".detail-close") {
      width = 32.px
      height = 32.px
      margin((-64).px, LinearDimension.auto, 0.px, LinearDimension.auto)
      fontSize = 32.px
    }
  }
  func()
}

fun RBuilder.title(title: String) = styledDiv {
  css {
    display = Display.flex
    width = 80.pct
    margin(30.px, LinearDimension.auto)
    child(".line") {
      flex(1.0)
      marginTop = 6.px
      borderTop(1.px, BorderStyle.solid, rgba(255, 255, 255, 0.2))
    }
    child(".text") {
      padding(0.px, 12.px)
      fontSize = 14.px
      fontWeight = FontWeight.w700
    }
  }
  div("line") {}
  div("text") { +title }
  div("line") {}
}


fun RBuilder.icon1(type: Int) = styledSpan {
  css {
    width = 12.px
    height = 12.px
    marginRight = 4.px
    bgImage(imgs1[type])
  }
}

fun RBuilder.icon2(type: Int) = styledSpan {
  css {
    width = 16.px
    height = 16.px
    verticalAlign = VerticalAlign.middle
    marginRight = 6.px
    display = Display.inlineBlock
    bgImage(imgs2[type])
  }
}

