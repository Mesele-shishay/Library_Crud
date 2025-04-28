# JavaFX Setup Instructions

To properly integrate JavaFX with your project, follow these steps:

## 1. Download JavaFX SDK

1. Download JavaFX SDK from the official website: https://gluonhq.com/products/javafx/

   - For JDK 8: Use JavaFX SDK 8
   - For JDK 11+: Use the latest JavaFX SDK (e.g., JavaFX 11, 17, or 21)

2. Extract the downloaded ZIP file to a location on your computer
   - Recommended: `C:\Program Files\Java\javafx-sdk-11.0.2\` (or your preferred location)

## 2. Configure Your IDE

### For VS Code:

1. Install the "Extension Pack for Java" extension in VS Code
2. Create a `.vscode` folder in your project root if it doesn't exist
3. Create a `settings.json` file in the `.vscode` folder with these settings:
   ```json
   {
     "java.project.referencedLibraries": [
       "lib/**/*.jar",
       "src/lib/**/*.jar",
       "C:/Program Files/Java/javafx-sdk-11.0.2/lib/*.jar"
     ]
   }
   ```
4. Create a `launch.json` file in the `.vscode` folder:
   ```json
   {
     "version": "0.2.0",
     "configurations": [
       {
         "type": "java",
         "name": "Launch JavaFX Application",
         "request": "launch",
         "mainClass": "application.Main",
         "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-11.0.2/lib\" --add-modules=javafx.controls,javafx.fxml"
       }
     ]
   }
   ```
5. Adjust the paths to match your JavaFX SDK location

### For IntelliJ IDEA:

1. Go to File → Project Structure → Libraries
2. Click the + button and select "Java"
3. Navigate to your JavaFX SDK lib folder (e.g., `C:\Program Files\Java\javafx-sdk-11.0.2\lib`)
4. Select all JAR files and click "OK"
5. Name the library "JavaFX-11" (or whatever version you downloaded)
6. Apply and click OK

### For Eclipse:

1. Right-click on your project in the Project Explorer
2. Select Build Path → Configure Build Path
3. Go to the Libraries tab
4. Click "Add External JARs..."
5. Navigate to your JavaFX SDK lib folder
6. Select all JAR files and click "Open"
7. Apply and close

## 3. Run Configuration

### For IDE Run Configuration:

Add these VM arguments to your run configuration:

```
--module-path "C:\path\to\javafx-sdk\lib" --add-modules=javafx.controls,javafx.fxml
```

### Using the Provided Batch File:

1. Edit the `run.bat` file in the project root
2. Update the `PATH_TO_FX` variable to point to your JavaFX lib directory
3. Run the batch file to start the application

## Troubleshooting

- If you see "package javafx.\* does not exist" errors, make sure JavaFX libraries are properly added to your classpath
- For newer Java versions (9+), you must use the module system arguments
- For Java 8, standard classpath configuration should work
