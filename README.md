# HSKA-Mirco-Service-Backend

This is a backend for an shop based on microservices used at Hochschule Karlsruhe in lesson "Verteilte Informations Systeme" for the masters degree.

## Getting Started

First of all clone the repository. Then build the docker dependencies and start docker-compose.
IMPORTANT: The customer-services aren't enabled. To enable them goto docker-composoe.yml and remove the comment signs (#)
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

4) Build application
 ```
 mvn clean install
 ```


5) Start project

 ```
 docker-compose up
 ```

## Getting Started
To get access to the microservice open the following urls:
- <a href="localhost:8761">localhost:8761 - The Eureka-Server</a> 
- <a href="localhost:9000">localhost:9000 - The products-core-service</a> 
- <a href="localhost:9001">localhost:9010 - The customers-core-service</a>
- <a href="localhost:9001">localhost:9020 - The category-core-service</a>
- <a href="localhost:9001">localhost:9100 - The customers-composite-service</a>
- <a href="localhost:9001">localhost:9200 - The product-composite-service</a>
- <a href="localhost:9001">localhost:9300 - The category-composite-service</a>
 
There are internal MySQL databases whose cannot be reached from outside


## Test 
Important: If put requests don't work, you need to set a request header: Content-Type: application/json

To start hystrix dir to cd support/Api-Gateway/ and enter mvn spring-boot:run

To test the services you can setup REST-calls. These are: 

|Request-Methode| URL                                	        | Meaning                                      	 |
|---|---|---|
|GET|http://localhost:9090/api/customers/{userId}| show a user |
|GET|http://localhost:9090/api/products/search/{searchPhrase}| search for a product with a search-phrase|
|GET|http://localhost:9090/api/products/view/{productId}| search for a product by id |
|PUT|http://localhost:9090/api/portfolio/products| creates a product if the category exists |
|DELETE|http://localhost:9090/api/portfolio/products/{productId}| delete a product |
|PUT|http://localhost:9090/api/portfolio/categories| creates a category |
|DELETE|http://localhost:9090/api/portfolio/categories/{categoryId}| deletes a category if it doesn't appear in a product |
|GET|http://localhost:9090/api/portfolio/categories/get| ONLY FOR TESTING shows all categories |
|GET|http://localhost:9090/api/portfolio/products/get| ONLY FOR TESTING shows all products |
|GET|http://localhost:8010/hystrix| show hystrix dashboard. There you need to enter e.g. http://localhost:9090/api/portfolio/actuator/hystrix.stream |

 
 
## Help - Useful commands
```
# stop container
docker stop customer-core-service

# remove container
docker rm customer-core-service

# start the product-service-MySQL database
docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6

# start the customer-core-service with a link to the database
docker run -p 9010:8080 --name customer-core-service --link eureka-server --link mysql-customer-service:mysql -d jadecoma/customer-core-service

# remove all stoped proceses
docker system prune

# remove images
docker rmi jadecoma/eureka-server

# start the application
docker-compose up

# start service in a way of continious integration (in the folder)
mvn clean package & docker-compose up portfolio-composite-service

# login to the product-service-database 
docker exec -it mysql-product-service mysql -u demo_user -p

## in the database-environment: 
SHOW DATABASES;
USE vis-shop-product;
SHOW TABLES;
SELECT * FROM product;
quit

```