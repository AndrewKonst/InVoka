package andrewkonst.invoka.initializers

import andrewkonst.invoka.core.api.initializer.Initializer
import andrewkonst.invoka.core.api.notification.NotificationNotifier
import javax.inject.Inject


internal class NotificationInitializer @Inject constructor(
    private val notificationNotifier: NotificationNotifier
) : Initializer {

    override fun initialize() {
        notificationNotifier.createNotificationChannel(notificationNotifier.defaultChannelInfo())
    }

}
