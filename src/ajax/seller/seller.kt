package index.ajax.seller

import index.ajax.Method
import index.ajax.ajax
import kotlinx.coroutines.flow.Flow

interface Seller {
  var name: String
  var description: String
  var deliveryTime: Double
  var score: Double
  var serviceScore: Double
  var foodScore:  Double
  var rankRate: Double
  var minPrice: Int
  var deliveryPrice: Double
  var ratingCount: Int
  var sellCount: Int
  var bulletin: String
  var supports: Array<Support>
  var avatar: String
  var pics: Array<String>
  var infos: Array<String>
}

interface Support {
  var type: Int
  var description: String
}

suspend fun sellerInfo(): Flow<Seller> {
  return Method.GET.ajax("/buyer/sellerInfo")
}