# Getting Started
This is a coding assignment for Backend Developer position. Completed by Yana Gorfan.


### To start the application locally
* Run maven wrapper script to install all neccessary dependencies:
[Unix](mvnw)
 || [Windows](mvnw.cmd)
* Request the .env file that contains DB secrets OR create your own file in the root folder and put there variables: DB_HOST, DB_PORT and DB_PASSWORD
* Add to application.properties following property: `spring.config.import=optional:file:.env[.properties]`
* Run the following script to start the application `mvn spring-boot:run`
* The server runs at http://localhost:5000

### Public endpoints

* GET: / => endpoint created for AWS health check service and returns default greeting message


* GET: /balance/{id} => returns the balance of recipient by id OR "NOT_FOUND" status if the recipient does not exist in DB. When the application starts, it will create default recipient with id=1, so you can try URL http://localhost:5000/balance/1 with Postman or other testing tool.


* POST: /withdraw/{id} => withdraws the money from recipients balance, returns the balance after operation OR "NOT_ACCEPTABLE" status if there is not enough money. The request body should contain the amount of withdraw.


* POST: /send => transfers money from sender to recipient and returns the id of operation OR "NOT_ACCEPTABLE" status if the amount of transfer bigger than 1000 OR "NOT_FOUND" status if sender or recipient does not exist in DB. The example of request body:

_{
"senderID": 1,
"recipientID": 1,
"amount": 100.0
}_

### AWS deploy
1. Build .jar or docker container with source code
2. Enter your aws account
3. Open VCP console and set new instance if you don't want to use default one
3. Open EC2 console and launch new instance for the application
4. Open IAM console and create a new role for EC2 instance profile with following policies: AWSElasticBeanstalkWebTier, AWSElasticBeanstalkWorkerTier. You can also add AWSElasticBeanstalkMulticontainerDocker if you are planning to use docker containers for future CI/CD pipeline
5. Open elastic beanstalk console and create new application. Set environment configurations using previously created ec2-key and role. Create the environment properties for RDS connection: DB_HOST, DB_PORT and DB_PASSWORD
6. Wait until beanstalk launch the environment and use the generated URL for requests with endpoints provided above


### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Amazon RDS + MySQL](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/CHAP_GettingStarted.CreatingConnecting.MySQL.html)
* [Launching EC2 instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EC2_GetStarted.html#ec2-launch-instance)
* [Creating IAM role](https://docs.aws.amazon.com/IAM/latest/UserGuide/getting-started.html#getting-started-roles)
* [Elastic Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/GettingStarted.html)

