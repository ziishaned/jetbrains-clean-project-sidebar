plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform")
}

group = "dev.zeeshan.jetbrains"
version = "0.1.0"

dependencies {
    testImplementation("junit:junit:4.13.2")

    intellijPlatform {
        intellijIdeaCommunity("2025.1")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "dev.zeeshan.jetbrains.clean-project-sidebar"
        name = "Clean Project Sidebar"
        version = project.version.toString()

        description = providers.fileContents(layout.projectDirectory.file("README.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"
            it.substringAfter(start).substringBefore(end).trim()
        }

        ideaVersion {
            sinceBuild = "251"
        }
    }
}

tasks {
    test {
        useJUnit()
    }
}
