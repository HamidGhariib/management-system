docker pull mysql:8.3.0
docker pull rabbitmq:3.13-management-alpine
mvn clean install -DskipTests
compose.yaml