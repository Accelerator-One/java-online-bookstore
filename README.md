# java-online-bookstore
( Project Status : Suspension )
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
      
    # User Operations

      # Adding Users
      $ curl localhost:8080/users/add -d email=abc@gmail.com -d password=12345678
        # Result : success
      $ curl localhost:8080/users/add -d email=abc@gmail.com -d password=12347865
        # Result : user_already_present
      $ curl localhost:8080/users/add -d email=abc@.com -d password=12345678
        # Result : invalid_email
      $ curl localhost:8080/users/add -d email=abcde@gmail.com -d password=1234
        # Result : invalid_password
   
      # Lists all users (Admin Access Scope only)
      $ curl localhost:8080/users/list -d secretKey=FAKE_ENTRY
        # Result : < NO_RESPONSE >
      $ curl localhost:8080/users/list -d secretKey=SECRET_KEY
        # Result : [ JSON_Response: Registered_Users ]

    ```
    ```bash
    
      # Inventory Operations

      # Add new Book (Admin Access Scope only)
      $ curl localhost:8080/books/add -d bookId=1 -d name=Spring -d quantity=1 -d secretKey=FAKE_ENTRY
        # Result : not_authorized
      $ curl localhost:8080/books/add -d bookId=1 -d name=Spring -d quantity=1  -d secretKey=SECRET_KEY
        # Result : book_added
      $ curl localhost:8080/books/add -d bookId=1 -d name=Spring -d quantity=1  -d secretKey=SECRET_KEY
        # Result : entry_updated
      $ curl localhost:8080/books/add -d bookId=-1 -d name=Spring -d quantity=1  -d secretKey=SECRET_KEY
        # Result : invalid_book_id
      $ curl localhost:8080/books/add -d bookId=1 -d name=A -d quantity=1 -d secretKey=SECRET_KEY
        # Result : invalid_book_name
      $ curl localhost:8080/books/add -d bookId=1 -d name=Spring -d quantity=0  -d secretKey=SECRET_KEY
        # Result : invalid_book_quantity

      # List Books (Authorized User Access Level Scope)
      $ curl localhost:8080/books/list -d email=abc@gmail.com -d password=12345678
      
    ```
    ```bash

      # Purchase Operations

      # Add purchase
      $ curl localhost:8080/purchase/add -d bookId=1 -d email=abc@.com -d quantity=1 -d password=12345678
        # Result : invalid_email
      $ curl localhost:8080/purchase/add -d bookId=1 -d email=abc@gmail.com -d quantity=0 -d password=12345678
        # Result : invalid_quantity
      $ curl localhost:8080/purchase/add -d bookId=-1 -d email=abc@gmail.com -d quantity=1 -d password=12345678
        # Result : invalid_bookId
      $ curl localhost:8080/purchase/add -d bookId=1 -d email=abc@gmail.com -d quantity=1 -d password=12345988
        # Result : invalid_user_credentials
      $ curl localhost:8080/purchase/add -d bookId=1 -d email=abc@gmail.com -d quantity=1 -d password=12345678
        # Result : purchase_complete
      $ curl localhost:8080/purchase/add -d bookId=1 -d email=abc@gmail.com -d quantity=100 -d password=12345678
        # Result : insufficient_stock

      # Any invalid operation / failed transaction will flag 'invalid_operation'
      

      # List all purchases (Admin Only)
      $ curl localhost:8080/purchase/list/all -d secretKey=SECRET_KEY

      # List all purchases by Email ( Authorized User Access Level Scope )
      $ curl localhost:8080/purchase/list/user -d email=abc@gmail.com -d password=12345678
 
      # List all purchases by bookId (Admin Only)
      $ curl localhost:8080/purchase/list/book -d bookId=1 -d secretKey=SECRET_KEY
 
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