FROM openjdk:16-alpine3.13


RUN apk update && apk add bash


WORKDIR /app


COPY gradle/ gradle

COPY gradlew build.gradle settings.gradle ./

COPY src ./src

RUN ./gradlew assemble

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/service-restaurant-0.0.1-SNAPSHOT.jar"]