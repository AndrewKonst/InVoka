package andrewkonst.invoka.ui.test

import andrewkonst.invoka.core.api.ComponentDependenciesProvider
import andrewkonst.invoka.core.api.findComponentDependencies
import andrewkonst.invoka.core.ui.Screen
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext


internal class TestScreen private constructor(
    private val component: TestScreenComponent,
) : Screen {

    constructor(
        componentContext: ComponentContext, dependencies: ComponentDependenciesProvider
    ) : this(
        TestScreenBuilder.build(dependencies.findComponentDependencies()).factory()
            .create(componentContext)
    )

    @Composable
    override fun Content(modifier: Modifier) {
        val state by component.someState.collectAsState()
        Box(modifier) {
            Text(text = state.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }


}
