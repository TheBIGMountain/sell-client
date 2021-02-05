package index.pages.index.content.goods.detail

import components.rating.RatingClick
import components.rating.RatingDesc
import components.rating.RatingType.*
import components.rating.rating
import index.ajax.product.Product
import index.components.count.count
import index.components.split.split
import index.modules.useDispatch
import index.modules.useSelector
import index.pages.index.content.goods.price
import index.store.Action
import index.store.Dispatch
import index.utils.formatDate
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.css
import styled.styledDiv
import kotlin.js.Date

private interface DetailProps: RProps {
  var product: Product
  var isShow: Boolean
  var backClick: (Event) -> Unit
}

private fun addFirst(product: Product, selectFoods: MutableMap<Int, Pair<Product, Int>>, dispatch: Dispatch)
= MainScope().launch {
  dispatch(Action.ChangeSelectFoods(selectFoods.apply { put(product.id, product to 1) }.toMap()))
}

private val ratingDesc = RatingDesc("全部", "推荐", "吐槽")

private val detail = functionalComponent<DetailProps> { props ->
  val selectFoods = useSelector { it.selectFoods }
  val dispatch = useDispatch()
  val (onlyContent, isOnlyContent) = useState(false)
  val (ratingType, setRatingType) = useState(ALL)

  styledDiv { css { child("div::-webkit-scrollbar") { display = Display.none } }
    detailStyle(props.isShow) {
      props.product.let { product ->
        div("food-content") {
          div("image-header") {
            img { attrs { src = product.icon } }
            div("back") {
              attrs.onClickFunction = props.backClick
              i("icon-arrow_lift") {}
            }
          }
          div("content") {
            h1("title") { +product.name }
            div("detail") {
              span("sell-count") { +"月售${product.sellCount}" }
              span("rating") { +"好评率${product.rating}%" }
            }
            price {
              span { +"￥${product.price}" }
            }
            div("count-wrapper") {
              count(product)
            }
            buy(selectFoods[product.id] == null) {
              attrs.onClickFunction = { addFirst(product, selectFoods.toMutableMap(), dispatch) }
              +"加入购物车"
            }
          }
          split()
          div("info") {
            h1("title") { +"商品信息" }
            p("text") { +product.description }
          }
          split()
          div("rating") {
            product.ratings.let { ratings ->
              h1("title") { +"商品评价" }
              rating(
                ratings = if (ratings == undefined) emptyArray() else ratings,
                desc = ratingDesc,
                isOnlyContent = onlyContent,
                ratingType = ratingType,
                ratingClick = RatingClick(
                  { setRatingType(ALL) },
                  { setRatingType(POSITIVE) },
                  { setRatingType(NEGATIVE) },
                  { isOnlyContent(!onlyContent) }
                )
              )
              div("rating-wrapper") {
                if (ratings == undefined || ratings.isEmpty())
                  div("no-rating") { +"暂无评价" }
                else {
                  ul {
                    val newRatings = if (onlyContent)
                      ratings.filter { it.text != "" }.toTypedArray()
                    else ratings

                    val typeRatings = if (ratingType != ALL)
                      newRatings.filter { it.rateType == ratingType.type }.toTypedArray()
                    else newRatings

                    typeRatings.forEach {
                      li("ratings-item") {
                        div("user") {
                          span("name") { +it.username }
                          img("avatar") { attrs { width = "12"; height = "12"; src = it.avatar } }
                        }
                        div("time") { +Date(it.rateTime.toLong()).formatDate() }
                        p("text") {
                          span(if (it.rateType == POSITIVE.type) "icon-thumb_up" else "icon-thumb_down") {}
                          +it.text
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

fun RBuilder.detail(product: Product, isShow: Boolean, backClick: (Event) -> Unit) = child(detail) {
  attrs.product = product
  attrs.isShow = isShow
  attrs.backClick = backClick
}
