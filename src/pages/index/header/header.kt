package index.pages.index.header


import index.components.star.star
import index.modules.useSelector
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


private val header = functionalComponent<RProps> {
  val (show, isShow) = useState(false)
  val seller = useSelector { it.seller }

  seller?.let {
    headerStyle {
      div("content-wrapper") {
        img { attrs { width = "64"; height = "64"; src = seller.avatar } }
        div("content") {
          div("title") {
            span("brand") {}
            span("name") { +seller.name }
          }
          div("description") {
            +"${seller.description}/${seller.deliveryTime}分钟送达"
          }
          div("support") {
            icon1(seller.supports[0].type)
            span("text") { +seller.supports[0].description }
          }
        }
        div("support-count") {
          attrs.onClickFunction = { isShow(true) }
          span("count") { +"${seller.supports.size}个" }
          i("icon-keyboard_arrow_right") {}
        }
      }
      div("bulletin-wrapper") {
        attrs.onClickFunction = { isShow(true) }
        span("bulletin-title") {}
        span("bulletin-text") { +seller.bulletin }
        i("icon-keyboard_arrow_right") {}
      }
      div("background") {
        img { attrs { src = seller.avatar; width = "100%"; height = "100%" } }
      }
      detail(show) {
        div("detail-wrapper") {
          div("detail-main") {
            h1("name") { +seller.name }
            div("star-wrapper") {
              star(48,  seller.score)
            }
            title("优惠信息")
            ul("supports") {
              seller.supports.forEach {
                li("support-item") {
                  icon2(it.type)
                  span("text") { +it.description }
                }
              }
            }
            title("商家公告")
            div("bulletin") {
              p("content") { +seller.bulletin }
            }
          }
        }
        div("detail-close") {
          attrs.onClickFunction = { isShow(false) }
          i("icon-close") {}
        }
      }
    }
  }
}

fun RBuilder.header() = child(header)
