plugins {
    id("invoka.android.library")
}

android {
    namespace = "andrewkonst.invoka.core.api"
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.dagger)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlinx.coroutines.android)

}
