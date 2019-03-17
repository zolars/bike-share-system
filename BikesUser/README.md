# README

## Functions

The application can be used by both of administers and students easily.

For administers, todo.

For students, todo.

## Launch Instruction

You can just double-click the file `BikeShareSystem-User.jar` or `BikeShareSystem-Admin.jar` to run this program (With JVM).

You can also use the command-line interface to run this program.

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   ```powershell
   java -jar BikeShareSystem-User.jar
   ```
   or
   ```powershell
   java -jar BikeShareSystem-Admin.jar
   ```

4. Operate with the GUI.

## Install Instruction

Double click the `Setup.bat` in order to install our software. Then You can find the `BikeShareSystem-*.jar` in your installation path.

We can also use the command-line interface to install this program everywhere you want with source files (`/src/application/*.java` and `MANIFEST-*.MF`).

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   For Admin-type:

   ```powershell
   javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java
   jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin .
   java -jar BikeShareSystem-Admin.jar
   ```
   
   or for User-type:

   ```powershell
   javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java
   jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin .
   java -jar BikeShareSystem-User.jar
   ```

4. You can find the `BikeShareSystem-User.jar` or `BikeShareSystem-Admin.jar` in your installation path.

## Get Help

Thank you for using this application. If you have any problems, don't hesitate and contact me with following e-mail:

- E-mail: xinyf_bupt@outlook.com

Besides, I hope you can give valuable advice back to me. It's really helpful for me to improve this application.