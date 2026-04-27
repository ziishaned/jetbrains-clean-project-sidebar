package dev.zeeshan.jetbrains.sidebar

import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator
import com.intellij.ide.projectView.PresentationData

class CleanProjectSidebarNodeDecorator : ProjectViewNodeDecorator {
    override fun decorate(node: ProjectViewNode<*>, data: PresentationData) {
        if (!CleanProjectSidebarSettings.getInstance().state.hideLibraryRootText) {
            return
        }

        if (ProjectSidebarNodeRules.isLibraryRootLocation(data.locationString)) {
            data.locationString = null
        }

        val coloredText = data.coloredText
        if (!coloredText.isNullOrEmpty()) {
            val hasLibraryRoot = coloredText.any { fragment ->
                fragment.text.trim().equals("library root", ignoreCase = true)
            }
            if (hasLibraryRoot) {
                data.clearText()
                coloredText.filterNot { fragment ->
                    fragment.text.trim().equals("library root", ignoreCase = true)
                }.forEach { fragment ->
                    data.addText(fragment.text, fragment.attributes)
                }
            }
        }
    }
}
