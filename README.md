# HSKA-Mirco-Service-Backend

This is a backend for an shop based on microservices used at Hochschule Karlsruhe in lesson "Verteilte Informations Systeme" for the masters degree.

## Getting Started

First of all clone the repository. Then build the docker dependencies and start docker-compose.

### Prerequisites

You'll need installed: 
- docker
- maven

 
### Installing and starting
1) clone project
2) dir to project
3) open terminal and type

```
mvn clean package

to build only one single module:
mvn install -pl produduct-core-service
```

4) Start start_dependencies - this will start an insatce of eureka-server at locahost:8761

```
compose run --rm start_dependencies
```

5) Start project

 ```
 docker-compose up
 ```

## Getting Started
To get access to the microservice open the following urls:
- <a href="localhost:8761">localhost:8761 - The Eureka-Server</a> 
- <a href="localhost:9000">localhost:9000 - The products-microservice</a> 
- <a href="localhost:9001">localhost:9001 - The customers-microservice</a>
 
There are internal MySQL databases whose cannot be reached from outside


## Test 
To test the services you can setup REST-calls. These are: 
| URL                                	| Meaning                                      	|
|------------------------------------	|----------------------------------------------	|
| localhost:9000/products/test/test  	| Product-Service tells "test" and is working  	|
| localhost:9000/customers/test/test 	| Customer-Service tells "test" and is working 	|
|                                    	|                                              	|
 
 
 
## Help - Useful commands
```
# stop all relevant container
docker stop customer-core-service product-core-service mysql-customer-service mysql-product-service hska-micro-service-backend_eureka-server_1 &

# remove all relevant container
docker rm customer-core-service product-core-service mysql-customer-service mysql-product-service hska-micro-service-backend_eureka-server_1

# start a eureka-server
docker run -p 8761:8761 --name eureka-server -d jadecoma/eureka-server

# start the product-service-MySQL database
docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6

# start the customer-service-MySQL database
docker run -p 3307:3306 --name mysql-customer-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-customer -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6

# start the customer-core-service with a link to the database
docker run -p 9098:8080 --name customer-core-service --link eureka-server --link mysql-customer-service:mysql -d jadecoma/customer-core-service

# remove all stoped proceses
docker system prune

# remove images
docker rmi jadecoma/eureka-server jadecoma/customer-core-service jadecoma/product-core-service mysql dadarek/wait-for-dependencies

# start start_dependencies - before starting the application 
docker-compose run --rm start_dependencies

# start the application
docker-compose up

# login to the product-service-database 
docker exec -it mysql-product-service mysql -u demo_user -p

## in the database-environment: 
SHOW DATABASES;
USE vis-shop-product;
SHOW TABLES;
SELECT * FROM product;
quit

```