plugins {
    val kotlinVersion = "1.6.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.10.1"
}

group = "org.seiki.plugin"
version = "1.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jitpack.io")
    mavenCentral()
}

fun skikoAwt(ver: String) = "org.jetbrains.skiko:skiko-awt-runtime-$ver"

val legacy = true

dependencies {
    if (legacy) {
        val skikoVer = "0.7.16"
        implementation(skikoAwt("windows-x64:$skikoVer"))
        implementation(skikoAwt("linux-x64:$skikoVer"))
        implementation(skikoAwt("linux-arm64:$skikoVer"))
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    } else {
        val smVer = "1.0.6"
        compileOnly("com.github.LaoLittle:SkikoMirai:$smVer")
        testImplementation("com.github.LaoLittle:SkikoMirai:$smVer")
        testImplementation("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.7.18")
    }
}