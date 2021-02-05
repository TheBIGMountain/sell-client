package index.components.cart

import index.components.count.count
import index.modules.History
import index.modules.useDispatch
import index.modules.useHistory
import index.modules.useSelector
import index.store.Action
import index.store.Dispatch
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent


private fun payDesc(totalPrice: Int, minPrice: Int) = when {
  totalPrice == 0 -> "￥${minPrice}元起送"
  totalPrice < minPrice -> "还差￥${minPrice - totalPrice}元起送"
  else -> "去结算"
}

private fun pay(totalPrice: Int, minPrice: Int, history: History) {
  if (totalPrice >= minPrice) history.push("/order")
}

private fun empty(dispatch: Dispatch) = MainScope().launch {
  launch { dispatch(Action.ChangeListShow(false)) }
  launch { dispatch(Action.ChangeSelectFoods(mapOf())) }
}

private val cart = functionalComponent<RProps> {
  val seller = useSelector { it.seller }
  val selectFoods = useSelector { it.selectFoods }
  val listShow = useSelector { it.isListShow }
  val dispatch = useDispatch()
  val history = useHistory()
  val totalPrice = selectFoods.values.fold(0) { before, value -> before + (value.first.price * value.second * 1000).toInt() }
  val totalCount = selectFoods.values.fold(0) { before, value -> before + value.second }

  seller?.let {
    cartStyle {
      div("content") {
        div("content-left") {
          attrs.onClickFunction = { if (totalCount > 0) dispatch(Action.ChangeListShow(!listShow)) }
          div("logo-wrapper") {
            logo(totalCount > 0) {
              i("icon-shopping_cart") {  }
            }
            div("num") { +"$totalCount" }
          }
          price(totalPrice > 0) { +"￥${totalPrice}元" }
          div("desc") { +"另需配送费￥${seller.deliveryPrice}元" }
        }
        div("content-right") {
          attrs.onClickFunction = { pay(totalPrice, seller.minPrice, history) };
          pay(totalPrice >= seller.minPrice) { +payDesc(totalPrice, seller.minPrice) }
        }
      }
      cartList(listShow) {
        div("list-header") {
          h1("title") { +"购物车" }
          span("empty") {  attrs.onClickFunction = { empty(dispatch) }; +"清空" }
        }
        div("list-content") {
          ul {
            selectFoods.entries.forEach {
              li("food") {
                span("name") { +it.value.first.name }
                div("price") { +"￥${it.value.first.price * it.value.second}" }
                div("count-wrapper") { count(it.value.first) }
              }
            }
          }
        }
      }
      listMask(listShow) { attrs.onClickFunction = { dispatch(Action.ChangeListShow(false)) } }
    }
  }
}

fun RBuilder.cart() = child(cart)