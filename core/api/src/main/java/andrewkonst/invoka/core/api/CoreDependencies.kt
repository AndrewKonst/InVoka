package andrewkonst.invoka.core.api

import andrewkonst.invoka.core.api.config.ConfigDependencies
import andrewkonst.invoka.core.api.dispatcher.AppDispatchersDependencies
import andrewkonst.invoka.core.api.json.JsonProviderDependencies
import andrewkonst.invoka.core.api.notification.NotificationNotifierDependencies
import android.content.Context


interface CoreDependencies :
    AppDispatchersDependencies,
    ConfigDependencies,
    NotificationNotifierDependencies,
    JsonProviderDependencies,
    ComponentDependencies {

    val context: Context

}
