plugins {
    id("invoka.android.library")
    id("invoka.android.library.compose")
}

android {
    namespace = "andrewkonst.invoka.core.ui"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.api)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.common)

    implementation(libs.decompose)
    implementation(libs.kotlinx.coroutines.android)

}