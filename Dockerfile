FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ivavprp.jar
ENTRYPOINT ["sh", "-c", "java -jar /ivavprp.jar"]
