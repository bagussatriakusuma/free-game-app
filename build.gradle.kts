import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.*

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.0.1")
        android.set(true)
        verbose.set(true)
        reporters {
            reporter(HTML)
        }
        filter {
            include("**/*.kt")
            exclude("**/build/**")
        }
    }
}