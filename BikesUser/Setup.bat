@echo off
echo Which version do you want to install? 
echo 1. Admininster Version 
echo 2. User Version 
echo 3. Cancel
CHOICE /C 123 /M "Please choose..."

if %errorlevel%==1 goto InstallAdminVersion
if %errorlevel%==2 goto InstallUserVersion
if %errorlevel%==3 goto Cancel 

:InstallAdminVersion
echo Installing admininster version...
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin .
echo You have successfully installed the Admininster Version!
pause
exit

:InstallUserVersion
echo Installing user version...
javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java .\src\database\*.java .\src\database\entity\*.java .\src\database\dao\*.java .\src\database\dao\impl\*.java
jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin .
echo You have successfully installed the User Version!
pause
exit

:Cancel
echo The installation has been canceled.
pause
exit