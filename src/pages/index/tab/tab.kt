package index.pages.index.tab



import index.modules.useHistory
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.*

private val map = mapOf(
  "/goods" to "商品",
  "/ratings" to "评论",
  "/seller" to "商家"
)

private val tab = functionalComponent<RProps> {
  val (active, setActive) = useState(0)
  val history = useHistory()

  useEffect(emptyList()) { setActive(map.keys.indexOf(window.location.hash.substring(1))) }

  tabStyle {
    map.entries.forEachIndexed { index, entry ->
      tabItem {
        attrs {
          if (index == active) classes = setOf("active")
          onClickFunction = { setActive(index); history.push(entry.key) }
        }; +entry.value
      }
    }
  }
}

fun RBuilder.tab() = child(tab)
