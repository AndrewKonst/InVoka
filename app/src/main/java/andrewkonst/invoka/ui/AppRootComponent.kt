package andrewkonst.invoka.ui

import andrewkonst.invoka.core.api.ComponentDependenciesProvider
import andrewkonst.invoka.core.ui.Screen
import andrewkonst.invoka.ui.test.TestScreen
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.parcelize.Parcelize


internal class AppRootComponent constructor(
    componentContext: ComponentContext,
    private val dependenciesProvider: ComponentDependenciesProvider,
) : ComponentContext by componentContext {

    private val stackNavigation = StackNavigation<AppRootScreenArg>()

    val rootStack = childStack(
        source = stackNavigation,
        initialConfiguration = AppRootScreenArg.Test,
        key = "rootStack",
        handleBackButton = true,
        childFactory = ::createFeatureComponent
    )

    private fun createFeatureComponent(
        feature: AppRootScreenArg,
        componentContext: ComponentContext,
    ): Screen = when (feature) {
        AppRootScreenArg.Test -> TestScreen(componentContext, dependenciesProvider)
    }

    sealed interface AppRootScreenArg : Parcelable {
        @Parcelize
        object Test : AppRootScreenArg
    }

}
