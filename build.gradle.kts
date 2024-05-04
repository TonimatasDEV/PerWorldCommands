plugins {
    java
}

val pluginVersion: String by extra

group = "dev.tonimatas.perworldcommands"
version = pluginVersion

base {
    archivesName.set("PerWorldCommands")
}

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.6-R0.1-SNAPSHOT")
}


tasks.withType<ProcessResources> {
    val replaceProperties = mapOf("pluginVersion" to pluginVersion)

    inputs.properties(replaceProperties)

    filesMatching("plugin.yml") {
        expand(replaceProperties)
    }
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(8)
}