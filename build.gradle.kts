import org.gradle.api.JavaVersion.VERSION_11

plugins {
    java
}

allprojects {
    group = "com.github.kunimido"
    version = "1.0-SNAPSHOT"
}

subprojects {
    if (name.matches(Regex("sg-contest-201.-exercise-.*"))) {
        apply<JavaPlugin>()

        repositories {
            mavenCentral()
        }

        dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
        }

        java {
            sourceCompatibility = VERSION_11
        }

        tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}
