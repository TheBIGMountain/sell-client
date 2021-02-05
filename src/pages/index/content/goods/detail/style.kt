package index.pages.index.content.goods.detail

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.detailStyle(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.fixed
    left = 0.px
    top = 0.px
    bottom = 48.px
    width = 100.pct
    backgroundColor = Color.white
    overflow = Overflow.auto
    if (isShow) {
      visibility = Visibility.visible
      transform { translateX(0.pct) }
    }
    else {
      visibility = Visibility.hidden
      transform { translateX(100.pct) }
    }
    transition("all", 0.5.s)
    child(".food-content") {
      position = Position.relative
      child(".image-header") {
        child("img") {
          width = 100.pct
          height = 100.pct
        }
        child(".back") {
          position = Position.absolute
          top = 10.px
          left = 0.px
          child(".icon-arrow_lift") {
            display = Display.block
            padding(10.px)
            fontSize = 20.px
            color = Color.white
          }
        }
      }
      child(".content") {
        padding(18.px)
        position = Position.relative
        child(".title") {
          lineHeight = LineHeight(14.px.value)
          marginBottom = 8.px
          fontSize = 14.px
          fontWeight = FontWeight.w700
          color = rgb(7, 17, 27)
        }
        child(".count-wrapper") {
          position = Position.absolute
          right = 12.px
          bottom = 12.px
        }
        child(".detail") {
          marginBottom = 18.px
          height = 10.px
          lineHeight = LineHeight(10.px.value)
          display = Display.flex
          children {
            fontSize = 10.px
            color = rgb(147, 153, 159)
          }
          child(".sell-count") { marginRight = 12.px }
        }
      }
      child(".info") {
        padding(18.px)
        child(".title") {
          lineHeight = LineHeight(14.px.value)
          marginBottom = 6.px
          fontSize = 14.px
          color = rgb(7, 17, 27)
        }
        child(".text") {
          lineHeight = LineHeight(24.px.value)
          padding(0.px, 8.px)
          fontSize = 12.px
          color = rgb(77, 85, 93)
        }
      }
      child(".rating") {
        paddingTop = 18.px
        child(".title") {
          lineHeight = LineHeight(14.px.value)
          fontSize = 14.px
          color = rgb(7, 17, 27)
          marginLeft = 18.px
        }
        child(".rating-wrapper") {
          child("ul .ratings-item") {
            padding(16.px, 18.px)
            borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
            child(".user") {
              float = Float.right
              right = 0.px
              top = 16.px
              height = 12.px
              lineHeight = LineHeight(height.value)
              child(".name") {
                verticalAlign = VerticalAlign.top
                fontSize = 10.px
                color = rgb(147, 153, 159)
                marginRight = 6.px
              }
              child(".avatar") {
                borderRadius = 50.pct
              }
            }
            child(".time") {
              height = 12.px
              lineHeight = LineHeight(12.px.value)
              fontSize = 10.px
              color = rgb(147, 153, 159)
              marginBottom = 6.px
            }
            child(".text") {
              height = 16.px
              lineHeight = LineHeight(height.value)
              fontSize = 12.px
              color = rgb(7, 17, 27)
              children {
                height = 24.px
                lineHeight = LineHeight(height.value)
                marginRight = 4.px
                fontSize = 12.px
              }
              child(".icon-thumb_up") { color = rgb(0, 160, 220) }
              child(".icon-thumb_down") { color = rgb(147, 153, 159) }
            }
          }
          child(".no-rating") {
            padding(16.px, 18.px)
            fontSize = 12.px
            color = rgb(147, 153, 159)
          }
        }
      }
    }
  }
  func()
}

fun RBuilder.buy(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position = Position.absolute
    right = 18.px
    bottom = 18.px
    zIndex = 1
    lineHeight = LineHeight(24.px.value)
    height = 24.px
    padding(0.px, 12.px)
    boxSizing = BoxSizing.borderBox
    fontSize = 10.px
    borderRadius = 12.px
    color = Color.white
    backgroundColor = rgb(0, 160, 220)
    if (isShow) {
      visibility = Visibility.visible
      opacity = 1
    }
    else {
      visibility = Visibility.hidden
      opacity = 0
    }
    transition("all", 0.5.s)
  }
  func()
}
