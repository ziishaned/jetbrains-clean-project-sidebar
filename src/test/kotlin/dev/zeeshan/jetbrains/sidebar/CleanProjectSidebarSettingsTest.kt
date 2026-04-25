package dev.zeeshan.jetbrains.sidebar

import org.junit.Assert.assertTrue
import org.junit.Test

class CleanProjectSidebarSettingsTest {
    @Test
    fun enablesBothSidebarCleanupOptionsByDefault() {
        val state = CleanProjectSidebarSettings.State()

        assertTrue(state.hideExternalLibraries)
        assertTrue(state.hideLibraryRootText)
    }
}
