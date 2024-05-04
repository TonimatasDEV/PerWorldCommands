plugins {
    java
}

val pluginVersion: String by extra

group = "dev.tonimatas.perworldcommands"
version = pluginVersion

base {
    archivesName.set("PerWorldCommands")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
    withSourcesJar()
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

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}