package andrewkonst.invoka.impl

import andrewkonst.invoka.BuildConfig
import andrewkonst.invoka.core.api.config.Config
import android.content.Context
import java.time.Duration


internal class AppConfig constructor(val context: Context) : Config {

    override val isDebug: Boolean = BuildConfig.DEBUG

    override val applicationId: String = BuildConfig.APPLICATION_ID

    override val apiTimeout: Duration = Duration.ofSeconds(30)

}
