package dev.zeeshan.jetbrains.sidebar

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class CleanProjectSidebarConfigurable : Configurable {
    private var settingsPanel: DialogPanel? = null
    private var workingState = CleanProjectSidebarSettings.State()

    override fun getDisplayName(): String = "Clean Project Sidebar"

    override fun createComponent(): JComponent {
        workingState = CleanProjectSidebarSettings.getInstance().state.copy()
        settingsPanel = panel {
            row {
                checkBox("Hide External Libraries")
                    .bindSelected(workingState::hideExternalLibraries)
            }
            row {
                checkBox("Hide library root text")
                    .bindSelected(workingState::hideLibraryRootText)
            }
        }
        return settingsPanel as DialogPanel
    }

    override fun isModified(): Boolean {
        settingsPanel?.apply()
        val saved = CleanProjectSidebarSettings.getInstance().state
        return workingState != saved
    }

    override fun apply() {
        settingsPanel?.apply()
        val saved = CleanProjectSidebarSettings.getInstance().state
        val changed = workingState != saved
        saved.hideExternalLibraries = workingState.hideExternalLibraries
        saved.hideLibraryRootText = workingState.hideLibraryRootText
        if (changed) {
            refreshProjectViews()
        }
    }

    override fun reset() {
        val saved = CleanProjectSidebarSettings.getInstance().state
        workingState.hideExternalLibraries = saved.hideExternalLibraries
        workingState.hideLibraryRootText = saved.hideLibraryRootText
        settingsPanel?.reset()
    }

    override fun disposeUIResources() {
        settingsPanel = null
    }

    private fun refreshProjectViews() {
        ProjectManager.getInstance().openProjects
            .filterNot { it.isDisposed }
            .forEach { project ->
                runCatching { ProjectView.getInstance(project).refresh() }
            }
    }
}
