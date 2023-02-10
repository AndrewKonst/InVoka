package andrewkonst.invoka.di.modules

import andrewkonst.invoka.core.impl.CoreImplModule
import dagger.Module


@Module(
    includes = [
        CoreImplModule::class,
        NotificationNotifierModule::class,
    ],
)
internal class CoreModule