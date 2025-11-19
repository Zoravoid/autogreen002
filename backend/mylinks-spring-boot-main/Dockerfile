FROM eclipse-temurin:8-jdk

WORKDIR /app  

COPY target/mylinks-spring-boot-1.0-SNAPSHOT.jar /app/MyLinksSpringBootApplication.jar

EXPOSE 8080

CMD [ "java", "-jar", "/app/MyLinksSpringBootApplication.jar" ]