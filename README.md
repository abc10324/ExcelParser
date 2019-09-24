# ExcelParser
parse .xls/.xlsx and return each row data  
  
## Environment  
 > Project: Gradle  
 > Framework: Spring MVC + Apache POI  
 > Server: Tomcat v9.0  

## Function  
 > Parse xls/xlsx format file  
 > End point : \<url\>/ExcelParser/parse   
 > Content-Type : multipart/form-data  
 > Upload file field name : file 

## how to use gradleDeploy.bat and gradleDebugDeploy.bat  
 1.set environment variable TOMCAT_HOME to your tomcat install location (ex:D:\apache-tomcat-9.0.22)  
 2.set environment variable JAVA_HOME to your java install location (ex:C:\Program Files\ojdkbuild\java-1.8.0-openjdk)  
 3.run gradleDeploy.bat/gradleDebugDeploy.bat(for remote debug, open port 8000)
 
