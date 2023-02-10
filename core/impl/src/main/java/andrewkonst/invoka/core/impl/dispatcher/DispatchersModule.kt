package andrewkonst.invoka.core.impl.dispatcher

import andrewkonst.invoka.core.api.dispatcher.AppDispatchers
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal abstract class DispatchersModule {

    @Binds
    @Reusable
    internal abstract fun bindDispatchers(impl: AppDispatchersImpl): AppDispatchers

}