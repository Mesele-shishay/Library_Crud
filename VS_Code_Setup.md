# Setting up JavaFX in VS Code

## Prerequisites

1. Java JDK 8 or later installed
2. VS Code installed
3. JavaFX SDK downloaded

## VS Code Extension Installation

1. Open VS Code
2. Go to Extensions view (Ctrl+Shift+X)
3. Search for "Extension Pack for Java"
4. Install the extension pack (includes Java language support, debugger, project manager, etc.)

## Project Configuration

The following configuration files have been created for you:

1. `.vscode/settings.json` - Configures Java project paths and libraries
2. `.vscode/launch.json` - Sets up run configuration for JavaFX
3. `.vscode/tasks.json` - Provides build tasks for the project

## Updating JavaFX Path

You need to update the JavaFX path in the following files to match your actual JavaFX SDK installation:

1. In `.vscode/settings.json`:

   ```json
   "java.project.referencedLibraries": [
     // ...
     "C:/path/to/your/javafx-sdk/lib/*.jar"
   ]
   ```

2. In `.vscode/launch.json`:

   ```json
   "vmArgs": "--module-path \"C:/path/to/your/javafx-sdk/lib\" --add-modules=javafx.controls,javafx.fxml"
   ```

3. In `.vscode/tasks.json`:

   ```json
   "command": "javac --module-path \"C:/path/to/your/javafx-sdk/lib\" --add-modules=javafx.controls,javafx.fxml ..."
   ```

4. In `run.bat`:
   ```batch
   set PATH_TO_FX="C:\path\to\your\javafx-sdk\lib"
   ```

## Running the Application

1. Open the project in VS Code
2. Press F5 or click the "Run and Debug" icon and select "Launch JavaFX Application"

## Building the Project

1. Press Ctrl+Shift+B to run the default build task
2. This will compile your Java classes with JavaFX dependencies

## Troubleshooting

- If you see red squiggly lines under JavaFX imports, make sure the path to JavaFX SDK is correct in settings.json
- If you get "Error: JavaFX runtime components are missing", check that your vmArgs in launch.json are correct
- For Java 8, you may not need the module-path arguments
