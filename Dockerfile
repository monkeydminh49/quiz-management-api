FROM openjdk:20
MAINTAINER MinhDunk
EXPOSE 8080
COPY target/quiz-api.jar quiz-api.jar
LABEL authors="MinhDunk"

ENTRYPOINT ["java", "-jar", "quiz-api.jar"]