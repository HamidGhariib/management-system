FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","--spring.config.location=../src/main/resources/application-docker.properties","/app.jar"]