import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform")
}

group = "dev.zeeshan.jetbrains"
version = "0.1.1"

val marketplaceToken = providers
    .gradleProperty("intellijPlatformPublishingToken")
    .orElse(providers.environmentVariable("JETBRAINS_MARKETPLACE_TOKEN"))

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

        changeNotes = """
            Initial release:
            <ul>
              <li>Add a toggle to hide the External Libraries node in the Project tool window.</li>
              <li>Add a toggle to hide library root text such as library root beside node_modules.</li>
            </ul>
        """.trimIndent()
    }

    publishing {
        token = marketplaceToken
        channels = listOf("default")
        hidden = false
    }

    pluginVerification {
        ides {
            ide(IntelliJPlatformType.IntellijIdeaCommunity, "2025.1")
        }
    }
}

tasks {
    buildPlugin {
        archiveFileName.set("jetbrains-clean-project-sidebar-${project.version}.zip")
        destinationDirectory.set(layout.buildDirectory.dir("distributions"))
    }

    test {
        useJUnit()
    }
}
