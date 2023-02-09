package andrewkonst.invoka

import org.gradle.api.JavaVersion


internal object AndroidConfig {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "33.0.2"
    const val minSdkVersion = 26
    const val targetSdkVersion = 33

    val javaVersion = JavaVersion.VERSION_11
}