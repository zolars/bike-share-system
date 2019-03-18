@echo off
md bin
md bin\images
echo Which version do you want to install? 
echo 1. Admininster Version 
echo 2. User Version 
echo 3. Station Version
echo 4. Cancel
CHOICE /C 1234 /M "Please choose..."

if %errorlevel%==1 goto InstallAdminVersion
if %errorlevel%==2 goto InstallUserVersion
if %errorlevel%==3 goto InstallStationVersion
if %errorlevel%==4 goto Cancel 

:InstallAdminVersion
echo Installing admininster version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin .
echo You have successfully installed the Admininster Version!
pause
exit

:InstallUserVersion
echo Installing user version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin .
echo You have successfully installed the User Version!
pause
exit

:InstallStationVersion
echo Installing station version...
copy src\images\* bin\images\
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
java -classpath bin application.Init
jar cfm BikeShareSystem-Station.jar .\MANIFEST\MANIFEST-STATION.MF -C bin .
echo You have successfully installed the Station Version!
pause
exit

:Cancel
echo The installation has been canceled.
pause
exit