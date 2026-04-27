package dev.zeeshan.jetbrains.sidebar

object ProjectSidebarNodeRules {
    private const val EXTERNAL_LIBRARIES_TEXT = "External Libraries"
    private const val EXTERNAL_LIBRARIES_CLASS = "ExternalLibrariesNode"
    private const val LIBRARY_ROOT_TEXT = "library root"

    fun isExternalLibrariesNode(className: String, presentableText: String?): Boolean =
        className.substringAfterLast('.') == EXTERNAL_LIBRARIES_CLASS ||
            presentableText == EXTERNAL_LIBRARIES_TEXT

    fun isLibraryRootLocation(locationText: String?): Boolean =
        locationText?.contains(LIBRARY_ROOT_TEXT, ignoreCase = true) == true
}
