// Adding the Kotlin plugin to the build file
plugins {
    id ("java")
    id ("org.springframework.boot") version "2.6.3"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("org.jetbrains.kotlin.jvm") version "1.6.10"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-web")
}

// Adding the task to create a jar package
tasks {
    withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
        enabled = false
    }
    withType<org.springframework.boot.gradle.tasks.bundling.BootWar> {
        enabled = false
    }
    withType<org.gradle.jvm.tasks.Jar> {
        enabled = true
        archiveFileName.set("Event.jar")
        archiveBaseName.set("Event")
        archiveVersion.set("")
        manifest {
            attributes["Implementation-Title"] = "Event"
            attributes["Implementation-Version"] = version
            attributes["Main-Class"] = "org.example.Main"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
