package dev.zeeshan.jetbrains.sidebar

import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator
import com.intellij.ide.projectView.PresentationData

class CleanProjectSidebarNodeDecorator : ProjectViewNodeDecorator {
    override fun decorate(node: ProjectViewNode<*>, data: PresentationData) {
        if (
            CleanProjectSidebarSettings.getInstance().state.hideLibraryRootText &&
            ProjectSidebarNodeRules.isLibraryRootLocation(data.locationString)
        ) {
            data.locationString = null
        }
    }
}
