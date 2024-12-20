FROM maven:3.4.1-jdk-21 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
COPY --from=build /target/quiz-app-0.0.1-SNAPSHOT.jar quiz-app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "quiz-app.jar"]

