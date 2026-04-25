package dev.zeeshan.jetbrains.sidebar

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.APP)
@State(
    name = "CleanProjectSidebarSettings",
    storages = [Storage("CleanProjectSidebarSettings.xml")],
)
class CleanProjectSidebarSettings : PersistentStateComponent<CleanProjectSidebarSettings.State> {
    data class State(
        var hideExternalLibraries: Boolean = true,
        var hideLibraryRootText: Boolean = true,
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        XmlSerializerUtil.copyBean(state, this.state)
    }

    companion object {
        fun getInstance(): CleanProjectSidebarSettings =
            ApplicationManager.getApplication().getService(CleanProjectSidebarSettings::class.java)
    }
}
