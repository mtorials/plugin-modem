// More about the setup here: https://github.com/DevSrSouza/KotlinBukkitAPI/wiki/Getting-Started
plugins {
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "de.mtorials"
version = "0.0.1"

repositories {
    jcenter()
    // minecraft
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")

    //kotlinbukkitapi with backup repo
    maven("http://nexus.devsrsouza.com.br/repository/maven-public/")
    
    //plugins
    maven("https://jitpack.io")
    maven("http://maven.sk89q.com/repo/")
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))

    //minecraft
    compileOnly("com.destroystokyo.paper:paper-api:1.16.2-R0.1-SNAPSHOT")

    //kotlinbukkitapi
    val changing = Action<ExternalModuleDependency> { isChanging = true }
    compileOnly("br.com.devsrsouza.kotlinbukkitapi:core:0.2.0-SNAPSHOT", changing)
    compileOnly("br.com.devsrsouza.kotlinbukkitapi:serialization:0.2.0-SNAPSHOT", changing)
    compileOnly("br.com.devsrsouza.kotlinbukkitapi:plugins:0.2.0-SNAPSHOT", changing)
    compileOnly("br.com.devsrsouza.kotlinbukkitapi:exposed:0.2.0-SNAPSHOT", changing)

    //plugins
    val transitive = Action<ExternalModuleDependency> { isTransitive = false }
    compileOnly("com.github.MilkBowl:VaultAPI:1.7", transitive)
    compileOnly("com.sk89q.worldedit:worldedit-core:7.2.0-SNAPSHOT", transitive)
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.2.0-SNAPSHOT", transitive)
    compileOnly("com.sk89q.worldguard:worldguard-core:7.0.4-SNAPSHOT", transitive)
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.4-SNAPSHOT", transitive)
    compileOnly("net.luckperms:api:5.1", transitive)
}

bukkit {
    main = "de.mtorials.modem.ModemPlugin"
    depend = listOf("KotlinBukkitAPI", "Vault", "WorldEdit", "WorldGuard", "LuckPerms")
    description = "a skypvp plugin"
    author = "mtorials"
    website = "https://mtorials.de"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    shadowJar {
        classifier = null
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(120, "seconds")
}