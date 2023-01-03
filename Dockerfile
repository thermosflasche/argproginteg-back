FROM amazoncorretto:17-alpine-jdk
MAINTAINER thermosflasche
COPY target/backend-0.0.1.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
