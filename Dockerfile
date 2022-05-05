FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=build/libs/service-restaurant-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} service-restaurant.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-restaurant-0.0.1-SNAPSHOT.jar"]