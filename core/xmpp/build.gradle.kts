import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.infophone.xmpp"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
// Use the new Kotlin compilerOptions API to set JVM target
tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Core Android support
    implementation(libs.smack.android){
        exclude(group = "xpp3", module = "xpp3") // usually not needed, only if conflicts appear
    }
    // TCP connection module
    implementation(libs.smack.tcp)
    // XMPP-IM (Instant Messaging) support (Roster, chats, etc.)
    implementation(libs.smack.im){
        exclude(group = "xpp3", module = "xpp3") // usually not needed, only if conflicts appear
    }
    // Various extensions for advanced features
    implementation(libs.smack.extensions){
        exclude(group = "xpp3", module = "xpp3") // usually not needed, only if conflicts appear
    }


    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}