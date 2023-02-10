package andrewkonst.invoka.core.api.notification

import andrewkonst.invoka.core.api.ComponentDependencies


interface NotificationNotifierDependencies : ComponentDependencies {

    fun provideNotificationNotifier(): NotificationNotifier

}