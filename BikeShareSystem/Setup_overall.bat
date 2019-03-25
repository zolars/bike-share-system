@echo off
md bin
md bin\images
echo Install all version...

echo Installing admininster version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin .
echo You have successfully installed the Admininster Version!

echo Installing user version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin .
echo You have successfully installed the User Version!

echo Installing station version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-Station.jar .\MANIFEST\MANIFEST-STATION.MF -C bin .
echo You have successfully installed the Station Version!

echo The installation has completed.
pause
exit