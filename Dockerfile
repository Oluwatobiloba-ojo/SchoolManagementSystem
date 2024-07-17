FROM gradle:8.9.0-jdk19 AS  build

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts

COPY src src

RUN chmod +x gradlew

RUN ./gradlew build

FROM openjdk:19-ea-1-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]