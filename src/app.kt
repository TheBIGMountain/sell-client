package index

import index.ajax.index
import index.ajax.product.categoryList
import index.ajax.ratings.ratings
import index.ajax.seller.sellerInfo
import index.modules.useDispatch
import index.pages.index.content.goods.goods
import index.pages.index.content.ratings.ratings
import index.pages.index.content.seller.seller
import index.pages.index.index.content.content
import index.pages.order.order
import index.pages.pay_info.payInfo
import index.store.Action
import index.store.Dispatch
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import modules.cookie
import react.*
import react.dom.div
import react.router.dom.hashRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch

private const val openIdIndex = 3
private const val openIdValue = 7
private const val openId = "openId"

private fun init(dispatch: Dispatch) = MainScope().launch {
  launch { sellerInfo().onEach { dispatch(Action.ChangeSeller(it)) }.collect() }
  launch { categoryList().onEach { dispatch(Action.ChangeCategoryList(it)) }.collect() }
  launch { ratings().onEach { dispatch(Action.ChangeRatingList(it)) }.collect() }
}

private fun RBuilder.validOpenId(func: RBuilder.() -> Unit) {
  if (cookie.load(openId) == null) {
    window.location.hash.substring(openIdIndex).let {
      if (!it.contains(openId))
        window.alert("请从微信客户端登录")
      else {
        cookie.save(openId, it.substring(openIdValue))
        window.location.href = index
      }
    }
  }
  else func()
}

private val app = functionalComponent<RProps> {
  val dispatch = useDispatch()

  validOpenId {
    useEffect(emptyList()) { init(dispatch) }
    hashRouter {
      switch {
        route("/order", render = { order() })
        route("/pay/:orderNo", render = { payInfo() })
        route("/", render = {
          content {
            switch {
              route("/goods", render = { goods() })
              route("/ratings", render = { ratings() })
              route("/seller", render = { seller() } )
              redirect("/", "/goods", exact = true)
              route("", render = { div { +"404 NOT FOUND!!!" } })
            }
          }
        })
      }
    }
  }
}

fun RBuilder.app() = child(app)
