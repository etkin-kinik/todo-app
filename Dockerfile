FROM openjdk:23-jdk

WORKDIR /app

COPY target/spring-boot-todo-application-0.0.1-SNAPSHOT.jar /app/spring-boot-todo-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-todo-application.jar"]