# Team:4 
# BorrowMyProducts
## About
- This application  helps people for renting/lending the products around them in this COVID-19 crucial time.
## How it Works
- Simple UI to show the products available near a user’s location.
- Provision to add the products that someone is willing to lend.
- Provision to notify the product owner when someone requests to borrow their product.
- Provision to notify the borrower when the owner accepts his product request.
## Features
- SMS notification through Twilio SMS api.
- Inventory management to keep track of user rentals. 
## Tech Stack
- Frontend:   Html , CSS , JavaScript , Thymeleaf
- Backend:   Spring Boot , JPA ,Database   
- Notifications: Twilio SMS API 
- Location : Google Maps API

## SetUp
### Requirements
- A Twilio account - sign up
- Maven
### Twilio Account Settings
- Before running the application, we need to collect all the config values we need to run the application:

 | Config Value | Description  |
 | ---- | ---- |
 | Account Sid  | Your primary Twilio account identifier - find this in the Console. |
 | Auth Token		| Used to authenticate - just like the above, you'll find this here. |
 | Service id		| Create a service in the the twilio verify services. |

### Local Development
- Clone the repository and cd into it.

  - git clone https://github.com/Hemasree5c2/BorrowMyProducts.git

- Set Twilio credentials using SPRING_APPLICATION_JSON properties on the command line with an environment variable.

- export SPRING_APPLICATION_JSON = '{"TWILIO":{"ACCOUNT_SID":"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","AUTH_TOKEN":"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","PATH_SERVICE_ID":"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"}}'

- Run the command 
  - mvn clean spring-boot:run
(or) 
  - ./mvnw spring-boot:run 

- Navigate to http://localhost:8080/

- The application is ready to use

## How to use it 
- Once the setup is done, open the browser and navigate to http://localhost:8080/signup
(or you can directly login with a demo user by navigating to http://localhost:8080/login and entering the following details:
 *Email: user@gmail.com*
 *Password: 1234*)
- Enter the details and allow the location
- Receive the Otp for provided number
- Enter the Otp and complete verification process
- Login with email and password
- On the home page, all the categories available will be visible.
- To borrow the product,click on the required category.
- On the category page, products in that category and which are near to the user’s location will be visible.
- The user can request for any product and the product owner will be notified about this request.
- Once the owner accepts the request, the user will be sent a success message with the contact details of the owner.
- The owner and the user can now contact each other and complete the lending process.
