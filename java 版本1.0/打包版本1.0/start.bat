@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_60
set path=%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
set CLASSPATH=.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar
java -jar weixinhttp.jar %1 %2 >> log.txt
@exit