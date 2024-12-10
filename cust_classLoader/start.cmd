@echo off

set JAVA_HOME=C:\Users\shenshuxin\.jdks\openjdk-21.0.1\bin\
echo %JAVA_HOME%

del .\x\Main.class
del .\y\Demo1.class
del .\z\Demo2.class
del demo1.jar
del demo2.jar
del main.jar

%JAVA_HOME%\javac.exe x\Main.java
%JAVA_HOME%\jar.exe cvf main.jar -C x .

%JAVA_HOME%\javac.exe z\Demo2.java
%JAVA_HOME%\jar.exe cvf demo2.jar -C z .

%JAVA_HOME%\javac.exe -cp demo2.jar y\Demo1.java
%JAVA_HOME%\jar.exe cvf demo1.jar -C y .


@REM %JAVA_HOME%\java.exe -cp main.jar Main
%JAVA_HOME%\java.exe -cp main.jar;demo1.jar Main
