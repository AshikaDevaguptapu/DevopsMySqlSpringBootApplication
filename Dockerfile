FROM openjdk:8
ADD target/sqljar.jar sqljar.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/sqljar.jar"]