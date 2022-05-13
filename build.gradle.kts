plugins {
    kotlin("jvm") version "1.7.0-Beta"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "me.laont.demo.kotlin.coffee.MainKt"
        }
    }
}