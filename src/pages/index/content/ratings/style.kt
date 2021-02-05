package index.pages.index.content.ratings

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.border
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderRight
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.ratingsStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    overflow = Overflow.auto
    position = Position.absolute
    top = 174.px
    left = 0.px
    bottom = 0.px
    width = 100.pct
    child(".ratings-content") {
      child(".overview") {
        padding(18.px, 0.px)
        display = Display.flex
        child(".overview-left") {
          flex(0.0, 0.0, 137.px)
          padding(6.px, 0.px)
          borderRight(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
          textAlign = TextAlign.center
          child(".score") {
            lineHeight = LineHeight(28.px.value)
            fontSize = 22.px
            color = rgb(255, 153, 0)
            marginBottom = 6.px
          }
          child(".title") {
            lineHeight = LineHeight(12.px.value)
            fontSize = 12.px
            color = rgb(7, 17, 27)
            marginBottom = 8.px
          }
          child(".rank") {
            lineHeight = LineHeight(10.px.value)
            fontSize = 10.px
            color = rgb(147, 153, 159)
          }
        }
        child(".overview-right") {
          width = 100.pct
          padding(6.px, 24.px)
          child(".score-wrapper") {
            lineHeight = LineHeight(18.px.value)
            marginBottom = 8.px
            display = Display.flex
            justifyContent = JustifyContent.center
            child(".title") {
              fontSize = 12.px
              color = rgb(7, 17, 27)
            }
            child("div") { margin(0.px, 12.px) }
            child(".score") {
              fontSize = 12.px
              color = rgb(255, 153, 0)
            }
          }
          child(".delivery-wrapper") {
            display = Display.flex
            fontSize = 12.px
            justifyContent = JustifyContent.center
            child(".title") {
              color = rgb(7, 17, 27)
            }
            child(".delivery") {
              marginLeft = 12.px
              color = rgb(147, 153, 159)
            }
          }
        }
      }
      child(".rating-wrapper") {
        child("ul .rating-item") {
          padding(18.px, 18.px)
          borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
          overflow = Overflow.hidden
          child(".avatar") {
            float = Float.left
            child("img") {
              borderRadius = 50.pct
            }
          }
          child(".content") {
            position = Position.relative
            marginLeft = 40.px
            child(".name") {
              marginBottom = 4.px
              lineHeight = LineHeight(12.px.value)
              fontSize = 10.px
              color = rgb(7, 17, 27)
            }
            child(".star-wrapper") {
              marginBottom = 6.px
              display = Display.flex
              lineHeight = LineHeight(12.px.value)
              height = 12.px
              children { verticalAlign = VerticalAlign.top }
              child(".delivery") {
                marginLeft = 6.px
                fontSize = 10.px
                color = rgb(147, 153, 159)
              }
            }
            child(".text") {
              marginBottom = 18.px
              lineHeight = LineHeight(18.px.value)
              color = rgb(7, 17, 27)
              fontSize = 12.px
            }
            child(".recommend") {
              lineHeight = LineHeight(16.px.value)
              fontSize = 0.px
              children {
                display = Display.inlineBlock
                margin(0.px, 8.px, 4.px, 0.px)
                fontSize = 9.px
              }
              child(".icon-thumb_up") {
                color = rgb(0, 160, 220)
              }
              child(".item") {
                padding(0.px, 6.px)
                paddingTop = 1.px
                border(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
                borderRadius = 1.px
                color = rgb(147, 153, 159)
                backgroundColor = Color.white
              }
            }
            child(".time") {
              position = Position.absolute
              top = 0.px
              right = 0.px
              lineHeight = LineHeight(12.px.value)
              fontSize = 10.px
              color = rgb(147, 153, 159)
            }
          }
        }
      }
    }
  }
  func()
}
