import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt.android.gradle.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    compileSdk = property("compileSdk").toString().toInt()

    defaultConfig {
        applicationId = property("applicationId").toString()
        minSdk = property("minSdk").toString().toInt()
        targetSdk = property("targetSdk").toString().toInt()
        versionCode = property("versionCode").toString().toInt()
        versionName = property("versionName").toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "quiz-${variant.name}-${variant.versionName}-${variant.versionCode}.apk"
                output.outputFileName = outputFileName
            }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = property("jvmTarget").toString()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    namespace = "com.dp.a360quiz"

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":usecase"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.fragment.ktx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)

    // Navigation
    implementation(libs.navigation.runtime)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Utilities
    implementation(libs.gson)
    implementation(libs.timber)

    // Testing
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.junit)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
}