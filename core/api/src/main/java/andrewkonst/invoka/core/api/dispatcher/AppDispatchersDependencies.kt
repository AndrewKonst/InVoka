package andrewkonst.invoka.core.api.dispatcher

import andrewkonst.invoka.core.api.ComponentDependencies


interface AppDispatchersDependencies : ComponentDependencies {

    fun provideAppDispatchers(): AppDispatchers

}