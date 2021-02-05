package index.ajax.ratings

import index.ajax.Method
import index.ajax.ajax
import kotlinx.coroutines.flow.Flow

interface Rating {
  var username: String
  var rateTime: Long
  var rateType: Int
  var text: String
  var avatar: String
  var deliveryTime: String
  var score: Int
  var recommend: Array<String>
}

suspend fun ratings(): Flow<Array<Rating>> {
  return Method.GET.ajax("/buyer/ratings")
}