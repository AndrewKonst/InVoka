package andrewkonst.invoka.core.ui.imageurl

import andrewkonst.invoka.core.api.config.Config


interface ImageUrlBuilder {

    fun buildUrl(config: Config): String

}
