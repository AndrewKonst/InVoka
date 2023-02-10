plugins {
    id("invoka.android.library")
    kotlin("kapt")
}

android {
    namespace = "andrewkonst.invoka.core.impl"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.api)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

}
