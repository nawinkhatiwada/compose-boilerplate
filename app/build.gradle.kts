plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android.ksp)
    alias(libs.plugins.dagger.android)
    alias(libs.plugins.kotlin.serialization)
}
val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val appVersion = versionMajor * 1000 + versionMinor * 100 + versionPatch
android {
    namespace = "com.devfinity.composeboilerplate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.devfinity.composeboilerplate"
        minSdk = 24
        targetSdk = 35
        versionCode = appVersion
        versionName = "$appVersion"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("int", "DB_VERSION", "$appVersion")
            buildConfigField("String", "SERVER_URL", "\"https://abc.com/api/\"")
        }

        debug {
            buildConfigField("int", "DB_VERSION", "$appVersion")
            buildConfigField("String", "SERVER_URL", "\"https://abc.com/api/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    implementation(libs.androidx.datastore.preferences)

    // Web Services
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.urlconnection)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.convertor.gson)
    implementation(libs.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Room db
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)


}