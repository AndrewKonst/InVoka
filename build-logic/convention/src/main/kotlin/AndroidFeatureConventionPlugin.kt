import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("invoka.android.library")
                apply("kotlin-kapt")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {

                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("implementation", (project(":core:ui")))

                add("implementation", libs.findLibrary("dagger").get())
                add("kapt", libs.findLibrary("dagger-compiler").get())
            }
        }
    }

}
