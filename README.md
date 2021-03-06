# user-registration using microservices
implementing user registration as part of an application that has a micro-service architecture


Here we are implementing user registration as part of an application that has a micro-service architecture. Users register by 
entering their email address and a password. The system then initiates an account creation workflow that includes creating the account in the database and sending an email to confirm their address. 

The following diagram shows the user registration service and how it fits into the overall system architecture.

![](https://github.com/tushargoel86/user-registration/blob/master/images/diagram.png)


The backend user registration service exposes a single RESTful endpoint for registering users. A registration request contains the user’s email address and password. The service verifies that a user with that email address has not previously registered and publishes a message notifying the rest of the system that a new user has registered. The notification is consumed by various other services including the user management service, which maintains user accounts, and the email service, which sends a confirmation email to the user.


```
File Structure

├───backend
│   ├───api-gateway-service
│   │   └───src
│   │       └───main
│   │           ├───java
│   │           │   └───com
│   │           │       └───tushar
│   │           │           └───crypto
│   │           │               └───apigateway
│   │           └───resources
│   ├───service-discovery
│   │   └───src
│   │       └───main
│   │           ├───java
│   │           │   └───org
│   │           │       └───tushar
│   │           │           └───user
│   │           └───resources
│   └───user-registration
│       ├───java
│       │   └───com
│       │       └───tushar
│       │           └───usermanagement
│       │               ├───config
│       │               ├───exception
│       │               ├───message
│       │               ├───model
│       │               ├───repository
│       │               ├───request
│       │               ├───resources
│       │               └───service
│       └───resources
├───images
└───ui
    └───src
        ├───components
        └───constants
```

Components Used:

# UI
* react
* semantic-ui-react
* hooks

# UI Install:
* create-react-app ui
* yarn i semantic-ui-css
* yarn i semantic-ui-react
* yarn i eslint-plugin-react-hooks

# User Form
![](https://github.com/tushargoel86/user-registration/blob/master/images/UserRegistrationForm.PNG)

# Success Message
![](https://github.com/tushargoel86/user-registration/blob/master/images/Success.PNG)

# Error Message
![](https://github.com/tushargoel86/user-registration/blob/master/images/Error.PNG)



# Backend
* Spring Boot
* API Gateway server (Zuul) 
* Service Discovery (Eureka)
* MongoDB
* AMQP (RabbitMQ)


