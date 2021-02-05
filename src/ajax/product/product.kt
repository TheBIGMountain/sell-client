package index.ajax.product

import index.ajax.Method
import index.ajax.ResultVo
import index.ajax.ajax
import index.ajax.ratings.Rating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Category {
  var name: String
  var type: Int
  var foods: Array<Product>
}

interface Product {
  var id: Int
  var name: String
  var price: Double
  var description: String
  var icon: String
  var sellCount: Int
  var rating: Int
  var ratings: Array<Rating>
}

suspend fun categoryList(): Flow<Array<Category>> {
  return Method.GET.ajax<ResultVo<Array<Category>>>("/buyer/product/list").map { it.data }
}
