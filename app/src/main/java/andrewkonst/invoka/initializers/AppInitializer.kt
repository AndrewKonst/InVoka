package andrewkonst.invoka.initializers

import andrewkonst.invoka.core.api.initializer.Initializer
import javax.inject.Inject


internal class AppInitializer @Inject constructor(
    private val initializerList: Set<@JvmSuppressWildcards Initializer>
) : Initializer {

    override fun initialize() {
        initializerList.forEach(Initializer::initialize)
    }

}
