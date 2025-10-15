FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/taskManager-0.0.1-SNAPSHOT.jar /app/taskManager-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/taskManager-0.0.1-SNAPSHOT.jar"]