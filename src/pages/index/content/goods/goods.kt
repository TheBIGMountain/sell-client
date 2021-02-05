package index.pages.index.content.goods


import index.ajax.product.Product
import index.components.cart.cart
import index.components.count.count
import index.modules.useSelector
import index.pages.index.content.goods.detail.detail
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onScrollFunction
import org.w3c.dom.events.Event
import org.w3c.dom.get
import react.*
import react.dom.*

private lateinit var state: GoodsState
private lateinit var setState: (GoodsState) -> Unit

private fun init() = MainScope().launch { setState(state.copy(heights = calcHeights())) }

private suspend fun calcHeights(): Array<Int> {
  var totalHeight = 0
  return document.getElementsByClassName("food-list-hook").unsafeCast<Array<dynamic>>().asFlow().withIndex()
    .map { it.value.offsetHeight.unsafeCast<Int>() }
    .map { totalHeight += it; totalHeight }
    .toList().toTypedArray()
}

private fun onScroll(e: Event) {
  e.target.asDynamic().scrollTop.unsafeCast<Int>().let { scrollTop ->
    state.heights.withIndex().forEach {
      if (scrollTop < it.value) {
        setState(state.copy(currentIndex = it.index))
        return@let
      }
    }
  }
}

private fun menuClick(index: Int) = MainScope().launch {
  setState(state.copy(currentIndex = index))
  document.getElementsByClassName("foods-wrapper")[0]!!
    .scrollTo(0.0, if (index == 0) 0.0 else state.heights[index - 1].toDouble())
}

private data class GoodsState(
  val heights: Array<Int> = emptyArray(),
  val currentIndex: Int = 0,
  val selectProduct: Product = jsObject {  },
  val isShowDetail: Boolean = false
)

private val goods = functionalComponent<RProps> {
  val categoryList = useSelector { it.categoryList }

  useState(GoodsState()).run { state = first; setState = second }
  useEffect(listOf(categoryList)) { init() }

  goodsStyle {
    div("menu-wrapper") {
      ul {
        categoryList.withIndex().forEach {
          menuItem(it.index == state.currentIndex) {
            attrs.onClickFunction = { _ -> menuClick(it.index) }
            if (it.value.type in (0..4)) icon3(it.value.type)
            span("text") { +it.value.name }
          }
        }
      }
    }
    div("foods-wrapper") {
      attrs { onScrollFunction = ::onScroll }
      ul {
        categoryList.forEach { category ->
          li("food-list food-list-hook") {
            h1("title") { +category.name }
            ul {
              category.foods.forEach { product ->
                li("food-item") {
                  img {
                    attrs {
                      src = product.icon
                      width = "57"
                      height = "57"
                      onClickFunction = { setState(state.copy(selectProduct = product, isShowDetail = true)) }
                    }
                  }
                  div("content") {
                    h2("name") { +product.name }
                    div("extra") {
                      span { +"月售${product.sellCount}" }
                      span { +"好评率${product.rating}%" }
                    }
                    price { span { +"￥${product.price}" } }
                    div("count-wrapper") {
                      count(product)
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    cart()
    detail(state.selectProduct, state.isShowDetail) {
      setState(state.copy(isShowDetail = false))
    }
  }
}


fun RBuilder.goods() = child(goods)
