docker pull mysql:8.3.0
docker pull rabbitmq:3.13-management-alpine
mvn clean install -DskipTests
compose.yaml

1-gateway
2-inventory
3-order
4-shipping