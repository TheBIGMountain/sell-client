package index.components.rating

import components.rating.RatingType
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderBottom
import kotlinx.html.DIV
import kotlinx.html.SPAN
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan


fun RBuilder.ratingStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child(".rating-type") {
      padding(18.px, 18.px)
      borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    }
  }
  func()
}

fun RBuilder.switch(isActive: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    padding(12.px, 18.px)
    height = 24.px
    lineHeight = LineHeight(height.value)
    borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
    color = rgb(147, 153, 159)
    fontSize = 0.px
    children { display = Display.inlineBlock }
    child(".icon-check_circle") {
      marginRight = 4.px
      fontSize = 24.px
    }
    child(".text") {
      fontSize = 12.px
      verticalAlign = VerticalAlign.top
    }
    if (isActive) child(".icon-check_circle") { color = Color("#00c850") }
  }
  func()
}

fun RBuilder.type(ratingType: RatingType, isActive: Boolean, func: StyledDOMBuilder<SPAN>.() -> Unit)
= styledSpan {
  css {
    display = Display.inlineBlock
    padding(8.px, 12.px)
    marginRight = 8.px
    borderRadius = 1.px
    fontSize = 12.px
    height = 16.px
    lineHeight = LineHeight(16.px.value)
    color = rgb(77, 85, 93)
    backgroundColor = rgba(0, 160, 220, 0.2)
    child(".count") {
      fontSize = 8.px
      marginLeft = 2.px
    }
    if (isActive) color = Color.white
    if (isActive) backgroundColor =  rgb(0, 160, 220)
    if (ratingType == RatingType.NEGATIVE) {
      backgroundColor = rgba(77, 85, 93, 0.2)
      if (isActive) backgroundColor =  rgb(77, 85, 93)
    }
  }
  func()
}