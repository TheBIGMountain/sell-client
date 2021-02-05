package index.ajax.order

import index.ajax.Method
import index.ajax.ajax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderForm (
  val name: String,
  val phone: String,
  val address: String,
  val openId: String,
  val cartDTO: CartDTO
)

class CartDTO (
  val products: Array<ProductInCart>
)

class ProductInCart (
  val productId: Int,
  val productName: String,
  val quantity: Int
)

class OrderDTO(
  val id: Int,
  val orderNo: String,
  val buyerName: String,
  val buyerPhone: String,
  val buyerAddress: String,
  val buyerOpenId: String,
  val amount: Double,
  val status: Int,
  val payStatus: Int,
  val orderDetails: Array<OrderDetail>,
  val createTime: Long
)

class OrderDetail(
  val id: Int,
  val orderNo: String,
  val productId: Int,
  val productQuantity: Int,
  val productName: String,
  val createTime: Long,
  val updateTime: Long
)

suspend fun OrderForm.createOrder(): Flow<String> {
  return Method.POST.ajax<dynamic>("/buyer/order/create", this)
    .map { it.data.orderNo.unsafeCast<String>() }
}

suspend fun String.orderDetail(orderNo: String): Flow<OrderDTO> {
  return Method.GET.ajax<dynamic>("/buyer/order/detail?orderNo=${orderNo}&openId=${this}")
    .map { it.data }
}