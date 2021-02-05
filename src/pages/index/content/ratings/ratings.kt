package index.pages.index.content.ratings

import components.rating.RatingClick
import components.rating.RatingType
import components.rating.RatingType.ALL
import components.rating.rating
import index.components.split.split
import index.components.star.star
import index.modules.useSelector
import index.utils.formatDate
import kotlinx.css.Display
import kotlinx.css.display
import react.*
import react.dom.*
import styled.css
import styled.styledDiv
import kotlin.js.Date

private val ratings = functionalComponent<RProps> {
  val seller = useSelector { it.seller }
  val ratingList = useSelector { it.ratingList }
  val (onlyContent, isOnlyContent) = useState(false)
  val (ratingType, setRatingType) = useState(ALL)

  seller?.let {
    styledDiv { css { child("div::-webkit-scrollbar") { display = Display.none } }
      ratingsStyle {
        div("ratings-content") {
          div("overview") {
            div("overview-left") {
              h1("score") { +"${seller.score.let { if (it == undefined) 0.0 else it }}" }
              div("title") { +"综合评分" }
              div("rank") { +"高于周边商家${seller.rankRate}%" }
            }
            div("overview-right") {
              div("score-wrapper") {
                span("title") { +"服务态度" }
                star(36, seller.serviceScore)
                span("score") { +"${seller.serviceScore}" }
              }
              div("score-wrapper") {
                span("title") { +"商品评分" }
                star(36, seller.foodScore)
                span("score") { +"${seller.foodScore}" }
              }
              div("delivery-wrapper") {
                span("title") { +"送达时间" }
                span("delivery") { +"${seller.deliveryTime}分钟" }
              }
            }
          }
          split()
          rating(
            ratings = ratingList,
            isOnlyContent = onlyContent,
            ratingType = ratingType,
            ratingClick = RatingClick(
              { setRatingType(ALL) },
              { setRatingType(RatingType.POSITIVE) },
              { setRatingType(RatingType.NEGATIVE) },
              { isOnlyContent(!onlyContent) }
            )
          )
          div("rating-wrapper") {
            ul {
              val newRatings = if (onlyContent)
                ratingList.filter { it.text != "" }.toTypedArray()
              else ratingList

              val typeRatings = if (ratingType != ALL)
                newRatings.filter { it.rateType == ratingType.type }.toTypedArray()
              else newRatings

              typeRatings.forEach {
                li("rating-item") {
                  div("avatar") {
                    img { attrs { src = it.avatar; width = "28"; height = "28" } }
                  }
                  div("content") {
                    h1("name") { +it.username }
                    div("star-wrapper") {
                      star(24, it.score.toDouble())
                      if (it.deliveryTime != undefined) {
                        span("delivery") { +it.deliveryTime }
                      }
                    }
                    if (it.text != "") p("text") { +it.text }
                    if (it.recommend != undefined && it.recommend.isNotEmpty()) {
                      div("recommend") {
                        span("icon-thumb_up") {}
                        it.recommend.forEach { item -> span("item") { +item } }
                      }
                    }
                    div("time") { +Date(it.rateTime).formatDate() }
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


fun RBuilder.ratings() = child(ratings)
