FROM maven:3.8.7-eclipse-temurin-19 AS build

RUN apt-get update && apt-get install -y wget unzip


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

FROM eclipse-temurin:19-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

