package andrewkonst.invoka.core.ui.lifecycle

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.launchAndCollectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend CoroutineScope.(T) -> Unit
) = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(minActiveState) { collect { action(it) } }
}


fun <T> Flow<T>.launchInLifecycle(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) = flowWithLifecycle(owner.lifecycle, minActiveState).launchIn(owner.lifecycleScope)


private const val StopTimeoutMillis: Long = 5000

/**
 * A [SharingStarted] meant to be used with a [StateFlow] to expose data to a view.
 *
 * When the view stops observing, upstream flows stay active for some time to allow the system to
 * come back from a short-lived configuration change (such as rotations). If the view stops
 * observing for longer, the cache is kept but the upstream flows are stopped. When the view comes
 * back, the latest value is replayed and the upstream flows are executed again. This is done to
 * save resources when the app is in the background but let users switch between apps quickly.
 */
val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)
