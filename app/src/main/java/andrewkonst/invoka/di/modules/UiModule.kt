package andrewkonst.invoka.di.modules

import andrewkonst.invoka.core.ui.externalrouter.ExternalRouter
import andrewkonst.invoka.impl.ExternalRouterImpl
import dagger.Binds
import dagger.Module


@Module
internal abstract class UiModule {

    @Binds
    internal abstract fun bindExternalRouter(impl: ExternalRouterImpl): ExternalRouter

}