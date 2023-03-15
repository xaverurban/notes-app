import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "ie.setu.controlflow"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    //for logging
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    implementation("org.slf4j:slf4j-simple:1.7.36")

    //For Streaming to XML and JSON
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
    implementation("org.codehaus.jettison:jettison:1.4.1")

    //For YAML
// https://mvnrepository.com/artifact/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:1.29")



}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}