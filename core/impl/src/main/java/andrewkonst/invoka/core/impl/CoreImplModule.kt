package andrewkonst.invoka.core.impl

import andrewkonst.invoka.core.impl.dispatcher.DispatchersModule
import andrewkonst.invoka.core.impl.json.JsonProviderModule
import dagger.Module

@Module(
    includes = [
        DispatchersModule::class,
        JsonProviderModule::class,
    ]
)
class CoreImplModule