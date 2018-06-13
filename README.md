# HSKA-Mirco-Service-Backend

This is a backend for an shop based on microservices used at Hochschule Karlsruhe in lesson "Verteilte Informations Systeme" for the masters degree.

## Architecture
![System-Architecture](http://www.jadecoma.de/architecture.png "System-Architecture")


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
|GET|<a href="http://localhost:9090/api/customers/1">http://localhost:9090/api/customers/{userId}</a>| show a user |
|GET|<a href="http://localhost:9090/api/products/search/Name">http://localhost:9090/api/products/search/{searchPhrase}</a>| search for a product with a search-phrase|
|GET|<a href="http://localhost:9090/api/products/view/1">http://localhost:9090/api/products/view/{productId}</a>| search for a product by id |
|PUT|<a href="http://localhost:9090/api/portfolio/products">http://localhost:9090/api/portfolio/products</a>| creates a product if the category exists |
|DELETE|<a href="http://localhost:9090/api/portfolio/products/1">http://localhost:9090/api/portfolio/products/{productId}</a>| delete a product |
|PUT|<a href="http://localhost:9090/api/portfolio/categories">http://localhost:9090/api/portfolio/categories</a>| creates a category |
|DELETE|<a href="http://localhost:9090/api/portfolio/categories/1">http://localhost:9090/api/portfolio/categories/{categoryId}</a>| deletes a category if it doesn't appear in a product |
|GET|<a href="http://localhost:9090/api/portfolio/categories/get">http://localhost:9090/api/portfolio/categories/get</a>| ONLY FOR TESTING shows all categories |
|GET|<a href="http://localhost:9090/api/portfolio/products/get">http://localhost:9090/api/portfolio/products/get</a>| ONLY FOR TESTING shows all products |
|GET|<a href="http://localhost:8010/hystrix">http://localhost:8010/hystrix</a>| show hystrix dashboard. There you need to enter e.g. http://localhost:9090/api/portfolio/actuator/hystrix.stream |

 
 
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