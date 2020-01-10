FROM openjdk:8-alpine
RUN mkdir -p /opt/ToDoUIService
WORKDIR /opt/ToDoUIService
COPY target/spring-thymeleaf-0.0.1-SNAPSHOT.jar /opt/ToDoUIService
CMD ["java", "-Dspring.profiles.active=k8s", "-jar", "spring-thymeleaf-0.0.1-SNAPSHOT.jar"]