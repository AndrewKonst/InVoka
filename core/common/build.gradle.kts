plugins {
    id("invoka.android.library")
}

android {
    namespace = "andrewkonst.invoka.core.common"
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.documentfile)

}
