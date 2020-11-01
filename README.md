# java-online-bookstore
[ UNDER DEVELOPMENT ]  
A simple server (endpoint) for managing an online bookstore web application for semester-project 2020

## Dependencies
- gradle
- open-jdk-11
- **spring-intializr** dependencies
  * spring-boot-starter-data-jpa
  * spring-boot-starter-web
  * mysql:mysql-connector-java

## Running the Project
1. Initialize the data
    - Login to your local mysql instance  
      ```bash
      
      $ sudo mysql -u root -p
      
      > create database bookstore;
      > create user 'admin'@'%' identified by 'password';
      > grant all on bookstore.* to 'admin'@'%';
      
      ```
      ( Security Lockup will be implemented in the later iterations )

2. Start the server :  
   ``$ ./gradlew bootRun``
   
3. Try the following test-cases:  
   ( Unit Testing will be implemented in later commits )
   ```bash
      
      # Adding Users
      $ curl localhost:8080/users/add -d email=abc@gmail.com -d password=12345678
        # Result : success
      $ curl localhost:8080/users/add -d email=abc@gmail.com -d password=12347865
        # Result : user_already_present
      $ curl localhost:8080/users/add -d email=abc@.com -d password=12345678
        # Result : invalid_email
      $ curl localhost:8080/users/add -d email=abcde@gmail.com -d password=1234
        # Result : invalid_password
   
      # Lists all users
      $ curl 'localhost:8080/users/list'
      
   ```
   
4. Restarting **mysql** instance :
   ```bash
      $ ps ax | grep sql
      # Now kill all MySQL PIDs using **sudo kill** <PID>
      
      # Kill processes
      $ sudo pkill mysql
      $ sudo pkill mysqld
      
      # Restart Service
      $ sudo service mysql restart
   ```
