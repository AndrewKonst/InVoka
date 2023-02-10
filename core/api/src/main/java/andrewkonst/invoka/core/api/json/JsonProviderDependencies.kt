package andrewkonst.invoka.core.api.json

import andrewkonst.invoka.core.api.ComponentDependencies


interface JsonProviderDependencies : ComponentDependencies {

    fun provideJsonProvider(): JsonProvider

}