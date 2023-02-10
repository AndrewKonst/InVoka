package andrewkonst.invoka.core.api

import android.app.Service
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass


interface ComponentDependencies

typealias ComponentDependenciesProvider = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)



inline fun <reified T : ComponentDependencies> ComponentDependenciesProvider.findComponentDependencies(): T {
    return this[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> ComponentActivity.findComponentDependencies(): T {
    val componentDependenciesProvider = (application as? HasComponentDependencies)?.dependencies
        ?: throw IllegalStateException("Can not find suitable dagger provider for $this")
    return componentDependenciesProvider[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Service.findComponentDependencies(): T {
    val componentDependenciesProvider = (application as? HasComponentDependencies)?.dependencies
        ?: throw IllegalStateException("Can not find suitable dagger provider for $this")
    return componentDependenciesProvider[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}


fun Fragment.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDependencies?) {
        current = current?.parentFragment
    }

    val hasDaggerProviders = current ?: when {
        activity is HasComponentDependencies -> activity as HasComponentDependencies
        activity?.application is HasComponentDependencies -> activity?.application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}