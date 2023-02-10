package andrewkonst.invoka.di.modules

import andrewkonst.invoka.core.api.notification.NotificationNotifier
import andrewkonst.invoka.impl.NotificationNotifierImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class NotificationNotifierModule {

    @Binds
    internal abstract fun bindNotificationNotifier(impl: NotificationNotifierImpl): NotificationNotifier

}