
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

val moduleName = "app"
kotlin {
    jvm()

    // This is for iPhone emulator
    // Switch here to iosArm64 to build library for iPhone device
    val ios = iosX64("ios") {
        binaries {
            framework {
                baseName = moduleName
            }
        }
    }
    sourceSets {
        val coroutines_version = "1.2.0"
        val ktor_version = "1.1.4"
        val serialization_version = "0.11.0"

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))

                implementation("com.eden:Clog4j:2.0.7")

                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val iosMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version")
                api("io.ktor:ktor-client-ios:$ktor_version")
                api("io.ktor:ktor-client-core-native:$ktor_version")
                api("io.ktor:ktor-client-json-native:$ktor_version")
                api("io.ktor:ktor-client-logging-native:$ktor_version")
            }
        }
        val iosTest by getting {
        }
    }

    // Create a task building a fat framework.
    tasks.create("copyFramework", FatFrameworkTask::class.java) {
        dependsOn("iosMainKlibrary")
        // The fat framework must have the same base name as the initial frameworks.
        baseName = moduleName

        // The default destination directory is '<build directory>/fat-framework'.
        val targetDir = project.properties["configuration.build.dir"] ?: buildDir.resolve("fat-framework/debug")
        destinationDir = file(targetDir)

        // Specify the frameworks to be merged.
        from(ios.binaries.getFramework("DEBUG"))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
}
