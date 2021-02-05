@file:JsModule("react-redux")
package index.modules

import index.store.Dispatch
import index.store.State

@JsName("useSelector")
external fun <T> useSelector(func: (State) -> T): T

@JsName("useDispatch")
external fun useDispatch(): Dispatch

