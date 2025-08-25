#FROM openjdk:8
#EXPOSE 8080
#COPY target/logger-receiver.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn clean package -DskipTests

# Step : Package image
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar" , "app.jar"]