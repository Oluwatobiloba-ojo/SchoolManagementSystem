FROM maven:3.8.5-openjdk-17 AS build

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

RUN chmod +x gradlew
RUN ./gradlew build

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /build/libs/quiz_application-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
