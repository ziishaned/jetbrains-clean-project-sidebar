package dev.zeeshan.jetbrains.sidebar

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProjectSidebarNodeRulesTest {
    @Test
    fun detectsExternalLibrariesByNodeClassName() {
        assertTrue(
            ProjectSidebarNodeRules.isExternalLibrariesNode(
                className = "com.intellij.ide.projectView.impl.nodes.ExternalLibrariesNode",
                presentableText = null,
            ),
        )
    }

    @Test
    fun detectsExternalLibrariesByVisibleText() {
        assertTrue(
            ProjectSidebarNodeRules.isExternalLibrariesNode(
                className = "com.example.OtherNode",
                presentableText = "External Libraries",
            ),
        )
    }

    @Test
    fun keepsOrdinaryProjectNodes() {
        assertFalse(
            ProjectSidebarNodeRules.isExternalLibrariesNode(
                className = "com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode",
                presentableText = "src",
            ),
        )
    }

    @Test
    fun detectsLibraryRootLocationText() {
        assertTrue(ProjectSidebarNodeRules.isLibraryRootLocation("library root"))
        assertTrue(ProjectSidebarNodeRules.isLibraryRootLocation(" Library Root "))
    }

    @Test
    fun keepsOtherLocationText() {
        assertFalse(ProjectSidebarNodeRules.isLibraryRootLocation(null))
        assertFalse(ProjectSidebarNodeRules.isLibraryRootLocation("generated sources root"))
    }
}
