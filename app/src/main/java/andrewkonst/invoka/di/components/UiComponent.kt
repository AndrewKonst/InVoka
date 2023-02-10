package andrewkonst.invoka.di.components

import andrewkonst.invoka.core.api.ComponentDependencies
import andrewkonst.invoka.core.api.ComponentDependenciesKey
import andrewkonst.invoka.core.api.CoreDependencies
import andrewkonst.invoka.core.api.scope.AppScope
import andrewkonst.invoka.core.ui.UiDependencies
import andrewkonst.invoka.ui.AppActivity
import andrewkonst.invoka.di.modules.UiModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@AppScope
@Component(
    dependencies = [CoreDependencies::class],
    modules = [UiComponent.UiComponentDependenciesModule::class, UiModule::class],
)
internal interface UiComponent : CoreDependencies, UiDependencies {

    fun inject(activity: AppActivity)

    object Holder {
        private var component: UiComponent? = null

        fun getOrCreate(
            coreDependencies: CoreDependencies,
        ): UiComponent {
            if (component == null) {
                component = DaggerUiComponent.builder()
                    .coreDependencies(coreDependencies)
                    .build()
            }
            return component!!
        }

        fun clear() {
            component = null
        }
    }


    @Module
    abstract class UiComponentDependenciesModule private constructor() {

        @[Binds IntoMap ComponentDependenciesKey(CoreDependencies::class)]
        abstract fun provideCoreDependencies(component: UiComponent): ComponentDependencies

        @[Binds IntoMap ComponentDependenciesKey(UiDependencies::class)]
        abstract fun provideCoreUiDependencies(component: UiComponent): ComponentDependencies

    }

}
