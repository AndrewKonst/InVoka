package andrewkonst.invoka.core.ui.externalrouter

import andrewkonst.invoka.core.api.ComponentDependencies


interface ExternalRouterDependencies : ComponentDependencies {

    fun provideExternalRouter(): ExternalRouter

}