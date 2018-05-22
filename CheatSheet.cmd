docker stop customer-core-service product-core-service mysql-customer-service mysql-product-service hska-micro-service-backend_eureka-server_1 &
docker rm customer-core-service product-core-service mysql-customer-service mysql-product-service hska-micro-service-backend_eureka-server_1

docker run -p 8761:8761 --name eureka-server -d jadecoma/eureka-server

docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-product -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6

docker run -p 3307:3306 --name mysql-customer-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=vis-shop-customer -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -d mysql:5.6
docker run -p 9098:8080 --name customer-core-service --link eureka-server --link mysql-customer-service:mysql -d jadecoma/customer-core-service


docker exec -it mysql-product-service mysql -u demo_user -p

docker system prune
docker rmi jadecoma/eureka-server jadecoma/customer-core-service jadecoma/product-core-service mysql dadarek/wait-for-dependencies
docker-compose run --rm start_dependencies
docker-compose up