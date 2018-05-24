docker stop customer-core-service product-core-service mysql-customer-service mysql-product-service eureka-server zuul &
docker rm customer-core-service product-core-service mysql-customer-service mysql-product-service eureka-server zuul

docker run -p 8761:8761 --name eureka-server -d jadecoma/eureka-server

docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6

docker run -p 3307:3306 --name mysql-customer-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-customer -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6
docker run -p 9098:8080 --name customer-core-service --link eureka-server --link mysql-customer-service:mysql -d jadecoma/customer-core-service


docker run -p 8761:8761 --name eureka-server -d jadecoma/eureka-server
docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6
docker run -p 9000:8080 --name product-core-service --link eureka-server --link mysql-product-service:mysql -d jadecoma/product-core-service
docker run -p 9000:8080 --name product-core-service --link mysql-product-service:mysql -d jadecoma/product-core-service

docker stop mysql-category-service category-core-service &
docker rm mysql-category-service category-core-service

docker run --name mysql-category-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-category -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6 &
docker run -p 9000:8080 --name category-core-service --link mysql-category-service:mysql -d jadecoma/category-core-service


docker exec -it mysql-category-service mysql -u demo_user -p

docker system prune
docker rmi jadecoma/eureka-server jadecoma/customer-core-service jadecoma/product-core-service mysql dadarek/wait-for-dependencies
docker-compose run --rm start_dependencies
docker-compose up




docker run -p 8761:8761 --name eureka-server -d jadecoma/eureka-server
docker run -p 9090:9090 --name zuul -d jadecoma/zuul
docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6
docker run -p 9000:8080 --name product-core-service --link eureka-server --link zuul --link mysql-product-service:mysql -d jadecoma/product-core-service
