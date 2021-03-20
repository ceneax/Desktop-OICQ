import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.compose") version "0.2.0-build132"
}

group = "ceneax.desktop"
version = "0.0.1"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }

    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")

    implementation(compose.desktop.currentOs)

    val miraiVersion = "2.4.2"
    api("net.mamoe", "mirai-core-api", miraiVersion) // 编译代码使用
    runtimeOnly("net.mamoe", "mirai-core", miraiVersion) // 运行时使用

//    implementation("com.husseinala.neon:glide:0.1.6")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "OICQ"
        }
    }
}