FROM amazoncorretto:17-alpine-jdk
MAINTAINER thermosflasche
COPY backend.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
