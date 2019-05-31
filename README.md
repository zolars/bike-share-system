# BikeShareSystem

## Description

A Java software used to manage the shared bikes on campus. An assignment from QMUL-EBU5304 to practice Scrum.

## Features

* Stable setup platform and easily Installation	

  - [x] Three Main class correspond to three types of software
    - [x] Easily test and add function pages
    - [x] Simplify global variables
  - [x] Setup.bat` and `Setup.sh` with version choice
    - [x] Windows version
    - [x] Mac version
    - [x] Linux version

* Appropriate swing GUI layout

  - [x] Main control and refresh layout

* Function Pages

  - [x] Default Layer page 

  - [x] Admin pages
    - [x] Register page
    - [x] Message send page
    - [x] All information search page
  - [x] Station pages
    - [x] Borrow bikes
    - [x] Return bikes
    - [x] Feedback

  (Optional)
  - [x] User pages
    - [x] Login page
    - [x] Bike’s amount observe page
    - [x] Message page
      - [x] Receive message function
      - [x] Feedback function
    - [x] History search page
    - [x] Setting page


* Replaceable Data Access Objects (Dao) with high Robustness

  - [x] BaseDao

    - [x] Entity
      - [x] Account
      - [x] Bikes
      - [x] Record
      - [x] Msg
    - [x] Dao
      - [x] AccountDao
      - [x] BikesDao
      - [x] RecordDao
      - [x] MsgDao
    - [x] DaoImpl
      - [x] AccountDaoImpl
      - [x] BikesDaoImpl
      - [x] RecordDaoImpl
      - [x] MsgDaoImpl

## Launch Instruction

You can just double-click the file `BikeShareSystem-User.jar` or `BikeShareSystem-Admin.jar` or `BikeShareSystem-Station.jar` to run this program (With JVM).

You can also use the command-line interface to run this program.

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   ```powershell
   java -jar BikeShareSystem-Admin.jar
   ```
   or
   ```powershell
   java -jar BikeShareSystem-Station.jar
   ```

4. Operate with the GUI.

## Install Instruction

Double click the `Setup.bat` in order to install our software. Then You can find the `BikeShareSystem-*.jar` in your installation path.

We can also use the command-line interface to install this program everywhere you want with source files (`*.java` and `MANIFEST-*.MF`).

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   For Admin-type:

   ```powershell
   javac -d bin\main -classpath bin\main .\src\main\application\*.java .\src\main\layout\*.java .\src\main\database\*.java .\src\main\database\entity\*.java .\src\main\database\dao\*.java .\src\main\database\dao\impl\*.java
   java -classpath bin\main application.Init
   jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin\main .
   ```
   
   or for Station-type:

   ```powershell
   javac -d bin\main -classpath bin\main .\src\main\application\*.java .\src\main\layout\*.java .\src\main\database\*.java .\src\main\database\entity\*.java .\src\main\database\dao\*.java .\src\main\database\dao\impl\*.java
   java -classpath bin\main application.Init
   jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin\main .
   ```
   
   (Optional)
   or for User-type:

   ```powershell
   javac -d bin\main -classpath bin\main .\src\main\application\*.java .\src\main\layout\*.java .\src\main\database\*.java .\src\main\database\entity\*.java .\src\main\database\dao\*.java .\src\main\database\dao\impl\*.java
   java -classpath bin\main application.Init
   jar cfm BikeShareSystem-Station.jar .\MANIFEST\MANIFEST-STATION.MF -C bin\main .
   ```

4. You can find the `BikeShareSystem-*.jar` in your installation path.

## Get Help

Thank you for using this application. If you have any problems, don't hesitate and contact me with following e-mail:

- Github: https://github.com/zolars/BikeShareSystem/tree/course-request
- E-mail: xinyf_bupt@outlook.com

Besides, I hope you can give valuable advice back to me. It's really helpful for me to improve this application.