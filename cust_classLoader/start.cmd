@echo off
:: 设置代码页为 UTF-8
chcp 65001

set JAVA_HOME=C:\Users\shenshuxin\.jdks\openjdk-21.0.1\bin\
echo %JAVA_HOME%

del .\x\*.class
del .\y\*.class
del .\z\*.class
del demo1.jar
del demo2.jar
del main.jar

%JAVA_HOME%\javac.exe x\Main.java
%JAVA_HOME%\jar.exe cvf main.jar -C x .

%JAVA_HOME%\javac.exe z\Demo2.java
%JAVA_HOME%\jar.exe cvf demo2.jar -C z .

%JAVA_HOME%\javac.exe -cp demo2.jar y\Demo1.java
%JAVA_HOME%\jar.exe cvf demo1.jar -C y .


echo 【使用双亲委派模型运行，正确示例】
%JAVA_HOME%\java.exe -cp main.jar Main

echo 【使用双亲委派模型运行，错误示例】
%JAVA_HOME%\java.exe -cp main.jar;demo1.jar Main

echo 【不使用双亲委派模型运行，正确示例】
%JAVA_HOME%\java.exe -cp main.jar;demo1.jar Main unused

echo 【不使用双亲委派模型运行，依然是正确示例】
%JAVA_HOME%\java.exe -cp main.jar Main unused
