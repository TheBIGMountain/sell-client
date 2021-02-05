package index.style

import kotlinx.css.*

fun CSSBuilder.bgImage(url: String) {
  background = "url('$url') center"
  backgroundSize = "contain"
  backgroundRepeat = BackgroundRepeat.noRepeat
}
