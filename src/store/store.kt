package index.store

import index.ajax.product.Category
import index.ajax.product.Product
import index.ajax.ratings.Rating
import index.ajax.seller.Seller
import redux.RAction
import redux.WrapperAction
import redux.createStore
import redux.rEnhancer

typealias Dispatch = (KAction) -> WrapperAction

val store = createStore(::reducer, State(), rEnhancer())

data class State(
  val seller: Seller? = null,
  val categoryList: Array<Category> = emptyArray(),
  val ratingList: Array<Rating> = emptyArray(),
  val selectFoods: Map<Int, Pair<Product, Int>> = HashMap(),
  val isListShow: Boolean = false,
  val favorite: Boolean = false,
)

interface KAction: RAction {
  fun execute(state: State): State
}

sealed class Action: KAction {
  class ChangeSeller(private val seller: Seller): Action() {
    override fun execute(state: State): State {
      return state.copy(seller = seller)
    }
  }
  class ChangeSelectFoods(private val selectFoods: Map<Int, Pair<Product, Int>>): Action() {
    override fun execute(state: State): State {
      return state.copy(selectFoods = selectFoods)
    }
  }
  class ChangeListShow(private val isListShow: Boolean): Action() {
    override fun execute(state: State): State {
      return state.copy(isListShow = isListShow)
    }
  }
  class ChangeFavorite(private val favorite: Boolean): Action() {
    override fun execute(state: State): State {
      return state.copy(favorite = favorite)
    }
  }
  class ChangeCategoryList(private val categoryList: Array<Category>): Action() {
    override fun execute(state: State): State {
      return state.copy(categoryList = categoryList)
    }
  }
  class ChangeRatingList(private val ratingList: Array<Rating>): Action() {
    override fun execute(state: State): State {
      return state.copy(ratingList = ratingList)
    }
  }
}

private fun reducer(state: State, action: RAction): State =
  if (action is KAction) action.execute(state)
  else state




