FROM openjdk:21
MAINTAINER MinhDunk
EXPOSE 8080
ADD target/quiz-api.jar quiz-api.jar
LABEL authors="MinhDunk"

ENTRYPOINT ["java", "-jar", "quiz-api.jar"]