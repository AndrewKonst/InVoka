package andrewkonst.invoka.core.api.dispatcher

import kotlinx.coroutines.CoroutineDispatcher


interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}