# Clean Project Sidebar

<!-- Plugin description -->
Clean Project Sidebar hides noisy Project tool window labels in JetBrains IDEs. It can hide the `External Libraries` node and gray library-root text such as `library root` next to folders like `node_modules`.
<!-- Plugin description end -->

![Before and after Project sidebar cleanup](assets/before-after.png)

## Features

- Hide the `External Libraries` node from the Project tool window.
- Hide gray library-root labels such as `library root` next to folders like `node_modules`.
- Toggle each cleanup independently from IDE settings.

## Settings

Open `Settings > Appearance & Behavior > Clean Project Sidebar`.

Both options are enabled by default:

- `Hide External Libraries`
- `Hide library root text`

## Install From Local Build

Build the plugin zip:

```bash
./gradlew buildPlugin
```

Then install `build/distributions/jetbrains-sidebar-0.1.0.zip` from `Settings > Plugins > Install Plugin from Disk...`.

## Development

```bash
./gradlew test
./gradlew buildPlugin
./gradlew runIde
```

## License

MIT
