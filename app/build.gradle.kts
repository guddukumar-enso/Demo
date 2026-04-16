import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.android.library)
//    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.infophone.app"
    compileSdk = 36

    defaultConfig {
       // applicationId = "com.infophone.app"
        minSdk = 24
        targetSdk = 36
       // versionCode = 1
      //  versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        /*debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }*/
    }

    /*flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationId = "com.infophone.dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "InfoPhone Dev")
            resValue("string", "base_url", "https://dev.api.com")
        }
        create("prod") {
            dimension = "environment"
            applicationId = "com.infophone.app"
            // no suffix for production
            resValue("string", "app_name", "InfoPhone")
            resValue("string", "base_url", "https://api.com")
        }
    }*/

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

// Use the new Kotlin compilerOptions API to set JVM target
tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    // 1. Dependency on the Core module
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))

    // 2. Dependencies on all Feature modules
    // This allows MainActivity and AppScreen to instantiate the NavEntryProviders
    // from the feature modules (OnboardNavEntryProvider, LoginNavEntryProvider, HomeNavEntryProvider).
    implementation(project(":features:auth"))
    implementation(project(":features:call"))
    implementation(project(":features:chat"))
    implementation(project(":features:contact"))
    implementation(project(":features:group"))
    implementation(project(":features:home"))
//    implementation(project(":features:media"))
    implementation(project(":features:setting"))
    implementation(project(":features:media"))
//    implementation(project(":features:setting"))
//    implementation(project(":features:workmode"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
}