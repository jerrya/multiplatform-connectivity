import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.jerrya"
version = "1.0.2"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        androidMain.dependencies {
            implementation(libs.androidx.startup)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutine)
        }
    }
}

android {
    namespace = "io.github.jerrya.connectivity.multiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "multiplaform-connectivity", version.toString())

    pom {
        name = "Multiplatform Connectivity"
        description = "Monitor device connectivity for iOS and Android with Kotlin Multiplatform."
        inceptionYear = "2024"
        url = "https://github.com/jerrya/multiplatform-connectivity/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "jerrya"
                name = "Jerry"
                url = "https://github.com/jerrya/"
            }
        }
        scm {
            url = "https://github.com/jerrya/multiplatform-connectivity/"
            connection = "scm:git:git://github.com/jerrya/multiplatform-connectivity.git"
            developerConnection = "scm:git:ssh://git@github.com/jerrya/multiplatform-connectivity.git"
        }
    }
}
