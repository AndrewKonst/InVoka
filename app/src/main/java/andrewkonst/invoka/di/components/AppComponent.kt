package andrewkonst.invoka.di.components

import andrewkonst.invoka.AppApplication
import andrewkonst.invoka.core.api.ComponentDependencies
import andrewkonst.invoka.core.api.ComponentDependenciesKey
import andrewkonst.invoka.core.api.CoreDependencies
import andrewkonst.invoka.core.api.scope.AppScope
import andrewkonst.invoka.initializers.AppInitializer
import andrewkonst.invoka.initializers.AppInitializerModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@AppScope
@Component(
    dependencies = [CoreDependencies::class],
    modules = [AppComponent.AppComponentDependenciesModule::class, AppInitializerModule::class],
)
internal interface AppComponent : CoreDependencies {

    fun inject(application: AppApplication)

    fun appInitializer(): AppInitializer

    companion object {
        fun create(
            coreDependencies: CoreDependencies,
        ): AppComponent {
            return DaggerAppComponent.builder()
                .coreDependencies(coreDependencies)
                .build()
        }
    }


    @Module
    abstract class AppComponentDependenciesModule {

        @Binds
        @IntoMap
        @ComponentDependenciesKey(CoreDependencies::class)
        abstract fun provideCoreDependencies(component: AppComponent): ComponentDependencies

    }

}
