plugins {
    id("invoka.android.application")
    id("invoka.android.application.compose")
}

android {
    namespace = "andrewkonst.invoka"

    defaultConfig {
        applicationId = "andrewkonst.invoka"
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            isShrinkResources = false
//            signingConfig = signingConfigs.getByName("debug")
//            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
//                mappingFileUploadEnabled = false
//            }
        }
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {

    implementation(projects.core.ui)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.material3)

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.profileinstaller)

    implementation(libs.coil.kt)

}
