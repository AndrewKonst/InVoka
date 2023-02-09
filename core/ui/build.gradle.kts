plugins {
    id("invoka.android.library")
    id("invoka.android.library.compose")
}

android {
    namespace = "andrewkonst.invoka.core.ui"
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

}