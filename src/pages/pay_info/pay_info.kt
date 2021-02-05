package index.pages.pay_info

import index.ajax.order.OrderDTO
import index.ajax.order.orderDetail
import index.components.split.split
import index.modules.useParams
import index.utils.formatDate
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import modules.cookie
import react.*
import react.dom.a
import react.dom.div
import react.dom.img
import react.dom.span
import kotlin.js.Date

private lateinit var orderDTO: OrderDTO
private lateinit var setOrderDTO: (OrderDTO) -> Unit

fun getOrderDetail(openId: String, orderNo: String) = MainScope().launch {
  openId.orderDetail(orderNo).onEach { setOrderDTO(it) }.collect()
}

private val payInfo = functionalComponent<RProps> {
  useState<OrderDTO>(jsObject {  }).run { orderDTO = first; setOrderDTO = second }

  val openId = cookie.load("openId")
  val orderNo = useParams().orderNo.unsafeCast<String>()

  useEffect(emptyList()) { getOrderDetail(openId!!, orderNo ) }

  payInfoStyle {
    div("user-info") {
      img { attrs { src = "/head.jpg"; width = "60"; height = "60" } }
      div { +"待接单" }
      a { +"取消订单" }
    }
    split()
    div("product-info") {
      div("title") {
        img { attrs { src = "/head.jpg"; width = "20"; height = "20" } }
        span { +"商品信息" }
      }
      orderDTO.orderDetails.let { orderDetails ->
        if (orderDetails != undefined) {
          orderDetails.forEach {
            div("food-info") {
              span { +"${it.productName} X ${it.productQuantity}" }
              span { +"￥${0.01 * it.productQuantity}" }
            }
          }
        }
      }
      div("amount-info") {
        span { +"实付" }
        span { +"￥${orderDTO.amount}" }
      }
    }
    split()
    div("delivery-info") {
      div("title") { +"配送信息" }
      div("deliver-time") {
        span { +"送达时间" }
        span { +"尽快送达" }
      }
      div {
        span { +"联系人姓名" }
        span { +orderDTO.buyerName }
      }
      div {
        span { +"联系人电话" }
        span { +orderDTO.buyerPhone }
      }
      div("address") {
        span { +"送货地址" }
        span { +orderDTO.buyerAddress }
      }
    }
    split()
    div("order-info") {
      div("title") { +"订单信息" }
      div("order-no") {
        span { +"订单号:" }
        span { +orderDTO.orderNo }
      }
      div("pay-type") {
        span { +"支付方式" }
        span { +"微信支付" }
      }
      div("pay-time") {
        span { +"下单时间" }
        span { +Date(orderDTO.createTime).formatDate() }
      }
    }
    split()
  }
}

fun RBuilder.payInfo() = child(payInfo)