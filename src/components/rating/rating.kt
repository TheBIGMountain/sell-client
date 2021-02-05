package components.rating

import index.ajax.ratings.Rating
import index.components.rating.ratingStyle
import index.components.rating.switch
import index.components.rating.type
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RProps
import react.child
import react.dom.div
import react.dom.span
import react.functionalComponent

private interface RatingProps: RProps {
  var ratings: Array<Rating>
  var desc: RatingDesc
  var isOnlyContent: Boolean
  var ratingType: RatingType
  var ratingClick: RatingClick
}

class RatingClick(
  val onClickAll: (Event) -> Unit,
  val onClickPositive: (Event) -> Unit,
  val onClickNegative: (Event) -> Unit,
  var onClickIsOnlyContent: (Event) -> Unit
)

class RatingDesc(
  val all: String = "全部",
  val positive: String = "满意",
  val negative: String = "不满意"
)

enum class RatingType(val type: Int) {
  POSITIVE(0), NEGATIVE(1), ALL(2)
}

private val rating = functionalComponent<RatingProps> { props ->
  ratingStyle {
    div("rating-type") {
      props.desc.let { desc ->
        props.ratings.let { ratings ->
          type(RatingType.ALL, props.ratingType == RatingType.ALL) {
            attrs.onClickFunction = props.ratingClick.onClickAll
            +desc.all; span("count") { +"${ratings.size}" }
          }
          type(RatingType.POSITIVE, props.ratingType == RatingType.POSITIVE) {
            attrs.onClickFunction = props.ratingClick.onClickPositive
            +desc.positive; span("count") { +"${ratings.filter { it.rateType == RatingType.POSITIVE.type }.size}" }
          }
          type(RatingType.NEGATIVE, props.ratingType == RatingType.NEGATIVE) {
            attrs.onClickFunction = props.ratingClick.onClickNegative
            +desc.negative; span("count") { +"${ratings.filter { it.rateType == RatingType.NEGATIVE.type }.size}" }
          }
        }
      }
    }
    switch(props.isOnlyContent) {
      attrs.onClickFunction = props.ratingClick.onClickIsOnlyContent
      span("icon-check_circle") {}
      span("text") { +"只看有内容的评价" }
    }
  }
}

fun RBuilder.rating(ratings: Array<Rating>, isOnlyContent: Boolean,
                    ratingType: RatingType, ratingClick: RatingClick,
                    desc: RatingDesc = RatingDesc())
= child(rating) {
  attrs.ratings = ratings
  attrs.desc = desc
  attrs.isOnlyContent = isOnlyContent
  attrs.ratingType = ratingType
  attrs.ratingClick = ratingClick
}
