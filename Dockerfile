#FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean install

FROM openjdk:21
MAINTAINER MinhDunk
EXPOSE 8080
#WORKDIR /app
COPY target/quiz-api.jar quiz-api.jar
LABEL authors="MinhDunk"

ENTRYPOINT ["java", "-jar", "quiz-api.jar"]