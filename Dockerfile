FROM maven:3.9.4-amazoncorretto-21 AS build
#WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN  mvn clean install

FROM openjdk:21
MAINTAINER MinhDunk
EXPOSE 8080
#WORKDIR /app
COPY --from=build target/quiz-api.jar quiz-api.jar
LABEL authors="MinhDunk"

ENTRYPOINT ["java", "-jar", "quiz-api.jar"]