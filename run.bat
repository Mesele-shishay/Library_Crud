@echo off
set PATH_TO_FX="C:\Users\Mesa\Downloads\Compressed\openjfx-24_windows-x64_bin-sdk\javafx-sdk-24\lib"
set CLASSPATH=out\production\javafx-mysql-crud;src\lib\mysql-connector-java-5.1.45-bin.jar;%PATH_TO_FX%\*

java --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.fxml application.Main

pause 