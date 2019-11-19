# Start with a base image containing Java runtime:
FROM openjdk:8-jdk-alpine

# Add Maintainer info:
MAINTAINER David <david8015@yahoo.com>

# Add a volume pointing to /tmp:
VOLUME /tmp

# Make port 80 available to the world outside this container:
EXPOSE 8761

# The application's JAR file:
ARG JAR_FILE=target/eureka-server-0.0.1-SNAPSHOT.jar

# Add the application's JAR to the container:
ADD ${JAR_FILE} eureka-server.jar

ENV SPRING_PROFILES_ACTIVE dev

# Run the jar file:
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar"]
