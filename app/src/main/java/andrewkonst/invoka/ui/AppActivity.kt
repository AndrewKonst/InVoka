package andrewkonst.invoka.ui

import andrewkonst.invoka.core.api.ComponentDependenciesProvider
import andrewkonst.invoka.core.api.HasComponentDependencies
import andrewkonst.invoka.core.api.config.Config
import andrewkonst.invoka.core.api.findComponentDependencies
import andrewkonst.invoka.core.ui.lifecycle.launchAndCollectIn
import andrewkonst.invoka.core.ui.theme.InvokaTheme
import andrewkonst.invoka.di.components.UiComponent
import andrewkonst.invoka.impl.ExternalRouterImpl
import andrewkonst.invoka.utils.ExternalRouterCommandHandler
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import javax.inject.Inject


internal class AppActivity : ComponentActivity(), HasComponentDependencies {


    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    @Inject
    lateinit var externalRouter: ExternalRouterImpl

    @Inject
    lateinit var config: Config


    override fun onCreate(savedInstanceState: Bundle?) {
        UiComponent.Holder.getOrCreate(findComponentDependencies()).inject(this)
        super.onCreate(savedInstanceState)

        initDecorSystemWindows()
        initExternalRouter()

        val root = AppRootComponent(defaultComponentContext(), dependencies)

        setContent {
            InvokaTheme {
                val childStack by root.rootStack.subscribeAsState()
                Children(
                    stack = childStack,
                    modifier = Modifier.fillMaxSize(),
                    content = { it.instance.Content(Modifier.fillMaxSize()) },
                )
            }
        }
    }

    private fun initDecorSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun initExternalRouter() {
        val handler = ExternalRouterCommandHandler(this, config)
        externalRouter.commands.launchAndCollectIn(this) { handler.handle(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            UiComponent.Holder.clear()
        }
    }

}
