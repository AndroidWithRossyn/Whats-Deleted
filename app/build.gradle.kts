plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tiriig.whatsdeleted"
    compileSdk = 35

    defaultConfig {
        applicationId = namespace
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        renderscriptTargetApi = 24
        renderscriptSupportModeEnabled = true
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        buildConfig = true
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        jniLibs.useLegacyPackaging = false
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    allprojects {
        gradle.projectsEvaluated {
            tasks.withType<JavaCompile> {
                options.compilerArgs.add("-Xlint:unchecked")
            }
        }
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.multidex)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.glide)
    ksp(libs.glide)
    annotationProcessor(libs.compiler)

    // Room components
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.androidx.room.testing)

    // Lifecycle components
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.common.java8)

    implementation (libs.androidx.lifecycle.process)

    // dagger-hilt
    implementation(libs.hilt.android)
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.compiler)

    //timber
    implementation(libs.timber)

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //App Intro
    implementation("com.github.AppIntro:AppIntro:6.2.0")
}