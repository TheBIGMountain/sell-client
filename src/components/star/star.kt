package index.components.star



import kotlinx.html.classes
import react.RBuilder
import react.RProps
import react.child
import react.dom.span
import react.functionalComponent

private const val LENGTH = 5
private const val CLS_ON = "on"
private const val CLS_HALF = "half"
private const val CLS_OFF = "off"

private interface StarProps: RProps {
  var size: Int
  var score: Double
}

private fun Double.starsStyle(): Array<String> {
  val starClasses = mutableListOf<String>()
  // 计算全星
  repeat(this.toInt()) { starClasses.add(CLS_ON) }
  // 计算半星
  if (this % 1 != 0.0) starClasses.add(CLS_HALF)
  // 计算无星
  while (starClasses.size < LENGTH) starClasses.add(CLS_OFF)
  return starClasses.toTypedArray()
}

private val star = functionalComponent<StarProps> { props ->
  starStyle {
    props.score.starsStyle().forEach {
      span("star-item star-${props.size} $it") {}
    }
  }
}

fun RBuilder.star(size: Int, score: Double) = child(star) { attrs.size = size; attrs.score = score }