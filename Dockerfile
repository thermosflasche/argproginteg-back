FROM amazoncorretto:17-alpine-jdk
MAINTAINER thermosflasche
COPY backend backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
