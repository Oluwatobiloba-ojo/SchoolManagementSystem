FROM maven:3.8.5-openjdk-17 AS build

#RUN apt-get update && apt-get install -y wget unzip && apt-get clean

#ENV GRADLE_VERSION=8.2
#RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp \
#    && unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip \
#    && rm /tmp/gradle-${GRADLE_VERSION}-bin.zip
#ENV PATH=/opt/gradle/gradle-${GRADLE_VERSION}/bin:${PATH}

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

RUN chmod +x gradlew
RUN ./gradlew build

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/quiz_application-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
