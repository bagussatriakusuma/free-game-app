plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("kotlin-android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    namespace = "com.example.challengechapter7"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.challengechapter7"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    flavorDimensionList += listOf("version")
    productFlavors {
        register("demo"){
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
            resValue(
                "string",
                "app_name",
                "Challenge Chapter 7 - Demo"
            )
        }
        register("full"){
            dimension = "version"
            applicationIdSuffix = ".full"
            versionNameSuffix = "-full"
            resValue(
                "string",
                "app_name",
                "Challenge Chapter 7"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":di"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Optional -- Robolectric environment
    testImplementation("androidx.test:core:1.5.0")
    // Optional -- Mockito framework
    testImplementation("org.mockito:mockito-core:5.3.1")
    // Optional -- mockito-kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    // Optional -- Mockk framework
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Jetpack Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("com.makeramen:roundedimageview:2.3.0")

    // Kotlin Couroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Retrofit
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Work Manager
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Avatar Image Generator
    implementation("com.github.amoskorir:avatarimagegenerator:1.5.0")

    // SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Image Picker
    implementation("com.github.dhaval2404:imagepicker:2.1")

    // Image Slider
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    // Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")
}

kapt {
    correctErrorTypes = true
    javacOptions {
        // Set the JVM target for kapt
        option("-source", "11")
        option("-target", "11")
    }

}