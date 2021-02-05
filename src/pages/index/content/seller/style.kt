package index.pages.index.content.seller

import index.style.bgImage
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderRight
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.dom.span
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan

private val imgs4 = arrayOf(
  "/resources/brand/decrease_4@2x.png",
  "/resources/brand/discount_4@2x.png",
  "/resources/brand/guarantee_4@2x.png",
  "/resources/brand/invoice_4@2x.png",
  "/resources/brand/special_4@2x.png",
)

private fun CSSBuilder.common() {
  padding(18.px, 0.px)
  children { padding(0.px, 18.px) }
  child(".title") {
    marginBottom = 8.px
    lineHeight = LineHeight(14.px.value)
    color = rgb(7, 17, 27)
    fontSize = 14.px
  }
}

fun RBuilder.sellerStyle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    overflow = Overflow.auto
    position = Position.absolute
    top = 174.px
    left = 0.px
    bottom = 0.px
    width = 100.pct
    child(".overview") {
      common()
      position = Position.relative
      child(".desc") {
        paddingBottom = 18.px
        display = Display.flex
        lineHeight = LineHeight(18.px.value)
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        children {
          firstChild {
            marginRight = 8.px
            children { paddingRight = 8.px }
          }
        }
        child(".text") {
          marginRight = 12.px
          fontSize = 10.px
          color = rgb(77, 85, 93)
        }
      }
      child(".remark") {
        display = Display.flex
        paddingTop = 18.px
        child(".block") {
          put("flex", "1")
          textAlign = TextAlign.center
          lineHeight = LineHeight(16.px.value)
          borderRight(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
          lastChild { borderRight = "none" }
          child("h2") {
            marginBottom = 4.px
            fontSize = 10.px
            color = rgb(147, 153, 159)
          }
          child(".content") {
            fontSize = 10.px
            color = rgb(7, 17, 27)
            child(".stress") {
              fontSize = 24.px
            }
          }
        }
      }
    }
    child(".bulletin") {
      common()
      paddingBottom = 0.px
      child(".content-wrapper") {
        padding(0.px, 18.px)
        paddingBottom = 12.px
        children { paddingLeft = 16.px }
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        child(".content") {
          lineHeight = LineHeight(24.px.value)
          fontSize = 12.px
          color = rgb(240, 20, 20)
        }
      }
      child(".supports") {
        child(".support-item") {
          padding(16.px, 12.px)
          fontSize = 0.px
          children { display = Display.inlineBlock }
          borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
          lastChild { borderBottom = "none" }
          child(".text") {
            lineHeight = LineHeight(16.px.value)
            fontSize = 12.px
            color = rgb(7, 17, 27)
          }
        }
      }
    }
    child(".pics") {
      common()
      child("div .pic-wrapper") {
        width = 100.pct
        overflow = Overflow.auto
        whiteSpace = WhiteSpace.nowrap
        child(".pic-list") {
          fontSize = 0.pc
          child(".pic-item") {
            display = Display.inlineBlock
            marginRight = 6.px
            width = 120.px
            height = 90.px
            lastChild { marginRight = 0.px }
          }
        }
      }
    }
    child(".info") {
      common()
      paddingBottom = 0.px
      color = rgb(7, 17, 27)
      child(".title") {
        marginBottom = 0.px
        paddingBottom = 12.px
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
      }
      child("ul .info-item") {
        padding(16.px, 12.px)
        lineHeight = LineHeight(16.px.value)
        borderBottom(1.px, BorderStyle.solid, rgba(7, 17, 27, 0.1))
        fontSize = 12.px
        lastChild { borderBottom = "none" }
      }
    }
  }
  func()
}

fun RBuilder.icon4(type: Int) = styledSpan {
  css {
    width = 16.px
    height = 16.px
    verticalAlign = VerticalAlign.top
    marginRight = 6.px
    display = Display.inlineBlock
    bgImage(imgs4[type])
  }
}

fun RBuilder.favorite(isFavorite: Boolean, onClick: (Event) -> Unit) = styledDiv {
  css {
    position = Position.absolute
    right = 18.px
    top = 18.px
    textAlign = TextAlign.center
    child(".icon-favorite") {
      display = Display.block
      marginBottom = 4.px
      lineHeight = LineHeight(24.px.value)
      fontSize = 24.px
      color = Color("#d4d6d9")
      if (isFavorite) {
        color = rgb(240, 20, 20)
      }
    }
    child(".text") {
      lineHeight = LineHeight(10.px.value)
      fontSize = 10.px
      color = rgb(77, 85, 93)
    }
  }
  span("icon-favorite") { attrs.onClickFunction = onClick }
  span("text") { +if (isFavorite) "已收藏" else "未收藏" }
}

