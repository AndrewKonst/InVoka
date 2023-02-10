package andrewkonst.invoka

import andrewkonst.invoka.core.api.ComponentDependenciesProvider
import andrewkonst.invoka.core.api.HasComponentDependencies
import andrewkonst.invoka.di.components.AppComponent
import andrewkonst.invoka.di.components.CoreComponent
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject


internal class AppApplication : Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        val core = CoreComponent.create(this)
        val appComponent = AppComponent.create(core)
        appComponent.inject(this)
        appComponent.appInitializer().initialize()
    }

    private companion object {

        init {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

    }

}
