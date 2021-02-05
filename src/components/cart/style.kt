package index.components.cart

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.cartStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.fixed
    left = 0.px
    bottom = 0.px
    zIndex = 50
    width = 100.pct
    height = 48.px
    overflow = Overflow.visible
    child(".content") {
      display = Display.flex
      backgroundColor = Color("#141d27")
      color = rgba(255, 255, 255, 0.4)
      child(".content-left") {
        flex(1.0)
        display = Display.flex
        child(".logo-wrapper") {
          transform { translateY((-10).px) }
          margin(0.px, 12.px)
          padding(6.px)
          width = 56.px
          height = 56.px
          boxSizing = BoxSizing.borderBox
          borderRadius = 50.pct
          backgroundColor = Color("#141d27")
          child(".num") {
            position = Position.absolute
            top = 0.px
            right = 0.px
            width = 24.px
            height = 16.px
            lineHeight = LineHeight(height.value)
            textAlign = TextAlign.center
            borderRadius = 16.px
            fontSize = 9.px
            fontWeight = FontWeight.w700
            color = Color.white
            backgroundColor = rgb(240, 20, 20)
            boxShadow(rgba(0, 0, 0, 0.4), 0.px, 4.px, 8.px, 0.px)
          }
        }
        child(".desc") {
          lineHeight = LineHeight(48.px.value)
          marginLeft = 12.px
          fontSize = 10.px
          paddingTop = 1.px
        }
      }
      child(".content-right") { width = 105.px }
    }
  }
  func()
}

fun RBuilder.cartList(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.absolute
    left = 0.px
    top = 0.px
    zIndex = -1
    width = 100.pct
    if (isShow) {
      visibility = Visibility.visible
      transform { translateY((-100).pct) }
    }
    else {
      visibility = Visibility.hidden
      transform { translateY((0).pct) }
    }
    transition("all", 0.5.s)
    child(".list-header") {
      height = 40.px
      lineHeight = LineHeight(height.value)
      padding(0.px, 18.px)
      backgroundColor = Color("#f3f5f7")
      borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
      child(".title") {
        float = Float.left
        fontSize = 17.px
        color = rgb(7, 17, 27)
      }
      child(".empty") {
        float = Float.right
        fontSize = 12.px
        color = rgb(0, 160, 220)
      }
    }
    child(".list-content ul::-webkit-scrollbar") { display = Display.none }
    child(".list-content ul") {
      padding(0.px, 18.px)
      maxHeight = 217.px
      backgroundColor = Color.white
      overflow = Overflow.auto
      child(".food") {
        position = Position.relative
        padding(12.px, 0.px)
        boxSizing = BoxSizing.borderBox
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        child(".name") {
          lineHeight = LineHeight(24.px.value)
          fontSize = 14.px
          color = rgb(7, 17, 27)
        }
        child(".price") {
          position = Position.absolute
          right = 90.px
          bottom = 12.px
          lineHeight = LineHeight(24.px.value)
          fontSize = 14.px
          fontWeight = FontWeight.w700
          color = rgb(240, 20, 20)
        }
        child(".count-wrapper") {
          position = Position.absolute
          right = 0.px
          bottom = 8.px
        }
      }
    }
  }
  func()
}

fun RBuilder.listMask(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.fixed
    top = 0.px
    left = 0.px
    width = 100.pct
    height = 100.pct
    zIndex = -10
    if (isShow) {
      visibility = Visibility.visible
      backgroundColor = rgba(7, 17, 27, 0.6)
      opacity = 1
    }
    else {
      visibility = Visibility.hidden
      backgroundColor = rgba(7, 17, 27, 0.0)
      opacity = 0
    }
    transition("all", 0.5.s)
  }
  func()
}

fun RBuilder.logo(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    height = 100.pct
    borderRadius = 50.pct
    backgroundColor = Color("#2b343c")
    textAlign = TextAlign.center
    child(".icon-shopping_cart") {
      fontSize = 24.px
      lineHeight = LineHeight(44.px.value)
      color = Color("#80858a")
    }
    if (isShow) {
      backgroundColor = rgb(0, 160, 220)
      child(".icon-shopping_cart") { color = Color.white }
    }
    else sibling(".num") { display = Display.none }
  }
  func()
}

fun RBuilder.price(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 12.px
    marginBottom = 24.px
    lineHeight = LineHeight(24.px.value)
    paddingRight = 12.px
    borderRight(1.px, BorderStyle.solid, rgba(255, 255, 255, 0.1))
    fontSize = 16.px
    fontWeight = FontWeight.w700
    if (isShow) { color = Color.white }
  }
  func()
}

fun RBuilder.pay(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 48.px
    lineHeight = LineHeight(height.value)
    textAlign = TextAlign.center
    fontSize = 12.px
    fontWeight = FontWeight.w700
    backgroundColor = Color("#2b333b")
    if (isShow) {
      backgroundColor = Color("#00b43c")
      color = Color.white
    }
  }
  func()
}