FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/java-k8s-cloud-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/ca.crt /app/ca.crt
ENTRYPOINT ["java","-jar","/app.jar"]
