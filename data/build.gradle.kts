plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation("androidx.room:room-common:2.6.0")
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

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
}