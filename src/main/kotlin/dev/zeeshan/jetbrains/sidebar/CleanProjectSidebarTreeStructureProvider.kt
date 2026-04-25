package dev.zeeshan.jetbrains.sidebar

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.util.treeView.AbstractTreeNode

class CleanProjectSidebarTreeStructureProvider : TreeStructureProvider {
    override fun modify(
        parent: AbstractTreeNode<*>,
        children: Collection<AbstractTreeNode<*>>,
        settings: ViewSettings?,
    ): Collection<AbstractTreeNode<*>> {
        if (!CleanProjectSidebarSettings.getInstance().state.hideExternalLibraries) {
            return children
        }

        return children.filterNot { child ->
            ProjectSidebarNodeRules.isExternalLibrariesNode(
                className = child.javaClass.name,
                presentableText = child.presentation.presentableText,
            )
        }
    }
}
