package andrewkonst.invoka.initializers

import andrewkonst.invoka.core.api.initializer.Initializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet


@Module
internal abstract class AppInitializerModule {

    @[Binds IntoSet]
    abstract fun imageInitializer(initializer: ImageInitializer): Initializer

    @[Binds IntoSet]
    abstract fun notificationInitializer(initializer: NotificationInitializer): Initializer

}
