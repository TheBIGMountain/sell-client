package index.pages.order

import index.ajax.index
import index.ajax.order.CartDTO
import index.ajax.order.OrderForm
import index.ajax.order.ProductInCart
import index.ajax.order.createOrder
import index.components.split.split
import index.modules.useSelector
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.html.INPUT
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import modules.cookie
import react.*
import react.dom.*
import kotlin.math.floor

private const val empty = ""

private lateinit var state: OrderState
private lateinit var setState: (OrderState) -> Unit

private data class OrderState(
  val name: String = empty,
  val phone: String = empty,
  val address: String = empty,
)

private fun pay(openId: String, cartDTO: CartDTO) = MainScope().launch {
  when {
    state.name == empty -> window.alert("联系人不能为空")
    state.phone == empty -> window.alert("联系电话不能为空")
    state.address == empty -> window.alert("送餐地址不能为空")
    else -> OrderForm(
      name = state.name,
      phone = state.phone,
      address = state.address,
      openId = openId,
      cartDTO = cartDTO
    ).createOrder().onEach {
      window.location.href = "http://proxy.springboot.cn/pay?" +
              "openid=oTgZpwb54GGy0m3iQCu4RhCrCqWI&" +
              "orderId=${it}&" +
              "returnUrl=${index}/success/${it}"
    }.collect()
  }
}

private val order = functionalComponent<RProps> {
  val openId = cookie.load("openId")
  useState(OrderState()).run { state = first; setState = second }

  val seller = useSelector { it.seller }
  val selectFoods = useSelector { it.selectFoods }
  val totalPrice = selectFoods.values.fold(0.0) { before, value -> before + value.first.price * value.second }

  seller?.let {
    orderStyle {
      div("form") {
        div("name") {
          span { +"联系人:" }
          input {
            attrs {
              placeholder = "您的姓名"
              value = state.name
              onChangeFunction = { setState(state.copy(name = it.target.unsafeCast<INPUT>().value)) }
            }
          }
        }
        div("phone") {
          span { +"联系电话:" }
          input {
            attrs {
              placeholder = "您的手机号"
              value = state.phone
              onChangeFunction = { setState(state.copy(phone = it.target.unsafeCast<INPUT>().value)) }
            }
          }
        }
        div("address") {
          span { +"送餐地址:" }
          input {
            attrs {
              placeholder = "您的送餐地址"
              value = state.address
              onChangeFunction = { setState(state.copy(address = it.target.unsafeCast<INPUT>().value)) }
            }
          }
        }
      }
      split()
      div("title") {
        img { attrs { src = seller.avatar; width = "30"; height = "30" } }
        span { +seller.name }
      }
      ul {
        selectFoods.forEach {
          li("food") {
            span { +it.value.first.name }
            span { +"${it.value.first.price} X ${it.value.second}"  }
          }
        }
      }
      div("amount-wrapper") {
        span { +"待支付 ￥${floor(totalPrice * 100) / 100}" }
        span { +"支付";
          attrs.onClickFunction = {
            pay(
              openId = openId!!,
              cartDTO = CartDTO(selectFoods.map { ProductInCart(it.key, it.value.first.name, it.value.second) }.toTypedArray()),
            )
          }
        }
      }
    }
  }
}

fun RBuilder.order() = child(order)