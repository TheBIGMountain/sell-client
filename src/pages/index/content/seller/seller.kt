package index.pages.index.content.seller


import index.components.split.split
import index.components.star.star
import index.modules.useDispatch
import index.modules.useSelector
import index.store.Action
import kotlinx.css.Display
import kotlinx.css.display
import react.RBuilder
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import styled.css
import styled.styledDiv


private val seller = functionalComponent<RProps> {
  val seller = useSelector { it.seller }
  val favorite = useSelector { it.favorite }
  val dispatch = useDispatch()

  seller?.let {
    styledDiv { css { child("div::-webkit-scrollbar") { display = Display.none } }
      sellerStyle {
        div("overview") {
          h1("title") { +seller.name }
          div("desc") {
            star(36, seller.score)
            span("text") { +"(${seller.ratingCount})" }
            span("text") { +"月售${seller.sellCount}单" }
          }
          ul("remark") {
            val titles = arrayOf("起送价", "商家配送", "平均配送时间")
            val stresses = arrayOf("${seller.minPrice}", "${seller.deliveryPrice}", "${seller.deliveryTime}")
            val contents = arrayOf("元", "元", "分钟")
            repeat(3) {
              li("block") {
                h2 { +titles[it] }
                div("content") {
                  span("stress") { +stresses[it] }; +contents[it]
                }
              }
            }
          }
          favorite(favorite) { dispatch(Action.ChangeFavorite(!favorite)) }
        }
        split()
        div("bulletin") {
          h1("title") { +"公告与活动" }
          div("content-wrapper") {
            p("content") { +seller.bulletin }
          }
          ul("supports") {
            seller.supports.forEach {
              li("support-item") {
                icon4(it.type)
                span("text") { +it.description }
              }
            }
          }
        }
        split()
        div("pics") {
          h1("title") { +"商家实景" }
          styledDiv { css { child("div::-webkit-scrollbar") { display = Display.none } }
            div("pic-wrapper") {
              ul("pic-list") {
                seller.pics.forEach {
                  li("pic-item") {
                    img { attrs { src = it; width = "120"; height = "90" } }
                  }
                }
              }
            }
          }
        }
        split()
        div("info") {
          h1("title") { +"商家信息" }
          ul {
            seller.infos.forEach {
              li("info-item") { +it }
            }
          }
        }
      }
    }
  }
}


fun RBuilder.seller() = child(seller)
