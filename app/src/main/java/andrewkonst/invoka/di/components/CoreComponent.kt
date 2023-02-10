package andrewkonst.invoka.di.components

import andrewkonst.invoka.impl.AppConfig
import andrewkonst.invoka.core.api.ComponentDependencies
import andrewkonst.invoka.core.api.ComponentDependenciesKey
import andrewkonst.invoka.core.api.CoreDependencies
import andrewkonst.invoka.core.api.config.Config
import andrewkonst.invoka.core.api.scope.AppScope
import andrewkonst.invoka.di.modules.CoreModule
import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@AppScope
@Component(
    modules = [
        CoreModule::class,
        CoreComponent.CoreComponentDependenciesModule::class,
    ],
)
internal interface CoreComponent : CoreDependencies {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun config(context: Config): Builder


        fun build(): CoreComponent
    }

    companion object {
        fun create(application: Application): CoreComponent {
            return DaggerCoreComponent.builder()
                .context(application.applicationContext)
                .config(AppConfig(application.applicationContext))
                .build()
        }
    }


    @Module
    abstract class CoreComponentDependenciesModule {

        @[Binds IntoMap ComponentDependenciesKey(CoreDependencies::class)]
        abstract fun provideCoreDependencies(component: CoreComponent): ComponentDependencies

    }

}
