package index.components.count

import index.ajax.product.Product
import index.modules.useDispatch
import index.modules.useSelector
import index.store.Action
import index.store.Dispatch
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div

private interface CountProps: RProps {
  var product: Product
}

private fun removeCart(id: Int, dispatch: Dispatch,
                       selectFoods: MutableMap<Int, Pair<Product, Int>>)
= MainScope().launch {
  selectFoods[id]?.let {
    if (it.second - 1 <= 0)
      selectFoods.remove(id)
    else
      selectFoods[id] = it.first to it.second - 1
  }.let {
    launch { dispatch(Action.ChangeSelectFoods(selectFoods.toMap())) }
    if (selectFoods.isEmpty()) launch { dispatch(Action.ChangeListShow(false)) }
  }
}

private fun addCart(product: Product, dispatch: Dispatch,
                    selectFoods: MutableMap<Int, Pair<Product, Int>>)
= MainScope().launch {
  selectFoods[product.id].let {
    if (it == null)
      selectFoods[product.id] = product to 1
    else
      selectFoods[product.id] = it.first to it.second + 1
  }.let { dispatch(Action.ChangeSelectFoods(selectFoods.toMap())) }
}

private val count = functionalComponent<CountProps> { props ->
  val (showBall, isShowBall) = useState(false)
  val (clientRect, setClientRect) = useState<dynamic>(jsObject {  })
  val selectFoods = useSelector { it.selectFoods }.toMutableMap()
  val dispatch = useDispatch()

  props.product.let { product ->
    countStyle(selectFoods[product.id]  != null) {
      div("cart-decrease icon-remove_circle_outline") {
        attrs.onClickFunction = {
          if (selectFoods[product.id] != null) {
            removeCart(product.id, dispatch, selectFoods)
          }
        }
      }
      div("cart-count") { +"${selectFoods[product.id]?.second ?: 0}" }
      div("cart-add icon-add_circle") {
        attrs.onClickFunction = {
          MainScope().launch {
            addCart(product, dispatch, selectFoods)
            setClientRect(it.target.asDynamic().getBoundingClientRect())
            isShowBall(true)
            delay(500)
            isShowBall(false)
          }
        }
        ball(showBall, clientRect)
      }
    }
  }
}

fun RBuilder.count(product: Product) = child(count) { attrs.product = product }