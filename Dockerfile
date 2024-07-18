FROM openjdk:19-jdk-slim AS build

RUN apt-get update && apt-get install -y wget unzip

RUN ./gradlew build --stacktrace

ENV GRADLE_VERSION=8.2
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp \
    && unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip \
    && rm /tmp/gradle-${GRADLE_VERSION}-bin.zip
ENV PATH=/opt/gradle/gradle-${GRADLE_VERSION}/bin:${PATH}

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

COPY src src

RUN chmod +x gradlew

RUN ./gradlew build

FROM openjdk:19-ea-1-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

