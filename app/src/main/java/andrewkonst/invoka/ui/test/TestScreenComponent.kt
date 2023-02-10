package andrewkonst.invoka.ui.test

import andrewkonst.invoka.core.api.dispatcher.AppDispatchers
import andrewkonst.invoka.core.ui.decompose.lifecycleScope
import andrewkonst.invoka.core.ui.lifecycle.WhileUiSubscribed
import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive


internal class TestScreenComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val context: Context,
    private val dispatchers: AppDispatchers,
) : ComponentContext by componentContext, CoroutineScope by componentContext.lifecycleScope() {

    private val someLogic = instanceKeeper.getOrCreate(TestScreenComponent::SomeLogic)

    val someState = flow {
        while (currentCoroutineContext().isActive) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }.stateIn(this, WhileUiSubscribed, 0)

    private class SomeLogic : InstanceKeeper.Instance {
        var data = ""
        val a = MutableStateFlow("")
        fun test() {

        }

        override fun onDestroy() {
            // Clean-up
        }
    }

}
