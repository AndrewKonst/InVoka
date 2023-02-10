package andrewkonst.invoka.core.api.config

import java.time.Duration


interface Config {

    val isDebug: Boolean

    val applicationId: String

    val apiTimeout: Duration

}
